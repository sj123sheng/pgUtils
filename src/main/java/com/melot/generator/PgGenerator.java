package com.melot.generator;

import com.melot.conf.SystemConfig;
import com.melot.utils.AttributeInfo;
import com.melot.utils.ClassUtils;
import com.melot.utils.FileSystemUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PgGenerator {
	
	public static void createPgFile(Map<String,Object>map){
		
	    String filePath = SystemConfig.getFilePath() + File.separator;

	    Map<String,String> procedure = getContent(map);
	    
	    for(Map.Entry<String, String>entry:procedure.entrySet()){
	        String fileName = entry.getKey()+".sql";
	        FileSystemUtils.writeFile(filePath, fileName.substring(fileName.lastIndexOf("\"") + 1 ), entry.getValue());
	    }
		
		String sequenceFile = map.get(ClassUtils.CLASS_ALIAS) +".sequence.sql";
		FileSystemUtils.writeFile(filePath, sequenceFile, createSequence(map));
		
		
	}
	
	private static Map<String,String> getContent(Map<String,Object>map){
		
	    Map<String,String>result  = new HashMap<String,String>();
	    
		Map<String,String> add = createAdd(map);
		Map<String,String> update = createUpdate(map);
		Map<String,String> delete = createDelete(map);
		Map<String,String> queryObject = createGet(map);
		Map<String,String> queryList = createQueryList(map);
	    
		result.put(add.get("name"),add.get("content"));
	    result.put(update.get("name"),update.get("content"));
	    result.put(delete.get("name"),delete.get("content"));
	    result.put(queryObject.get("name"),queryObject.get("content"));
	    result.put(queryList.get("name"),queryList.get("content"));
	    
		return result;
	}
	
	private static String createSequence(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		buffer.append("CREATE SEQUENCE " + "\""+SystemConfig.getPgNameSpace()+"\".\"seq_"+map.get(ClassUtils.TABLE_NAME)).append("\"\n");
		buffer.append("  INCREMENT 1").append("\n");
		buffer.append("  MINVALUE 1").append("\n");
		buffer.append("  MAXVALUE 99999999").append("\n");
		buffer.append("  START 1").append("\n");
		buffer.append("  CACHE 1;").append("\n");
		buffer.append(" ALTER TABLE "+ "\""+SystemConfig.getPgNameSpace()+"\".\"seq_"+map.get(ClassUtils.TABLE_NAME)+"\"" +" OWNER TO \""+ SystemConfig.getPgNameSpace() +"\";");
		return buffer.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	private static Map<String,String> createAdd(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		Map<String,String>result  = new HashMap<String,String>();
		buffer.append("CREATE OR REPLACE FUNCTION ");
		String processName ="\"" + SystemConfig.getPgNameSpace()+"\".\"p_"+map.get(ClassUtils.TABLE_NAME)+"_insert";
		List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		String pramiryName = primary.getName();
		String params = ""; //存储过程参数列表
		for(AttributeInfo attr : attributes){
			if(attr.getName().equals(pramiryName) || "timestamp".equals(attr.getTableType())){
				continue; //主键采用数据库自增,不需要传入存储过程
			}
			params += " IN i_"+attr.getTableName()+ " "+attr.getTableType()+",";
		}
		params += "OUT o_" + primary.getTableName() + " "+primary.getTableType(); 
		String strHead = processName+"\"" + "("+params+")";
		buffer.append(strHead).append("\n");
		buffer.append(" RETURNS "+primary.getTableType()+" AS $BODY$").append("\n");
		buffer.append("begin").append("\n");
		//buffer.append("  o_" + primary.getTableName()+" :="+"i_" + primary.getTableName() + ";").append("\n");
		buffer.append("  o_" + primary.getTableName()+" :="+"nextval(\'"+SystemConfig.getPgNameSpace()+".seq_"+map.get(ClassUtils.TABLE_NAME)+"\');").append("\n");
		String insert = "";
		String values = "";
		for(AttributeInfo attr : attributes){
			insert+=attr.getTableName()+",";
			if(attr.getName().equals(pramiryName)){
				values += "o_"+attr.getTableName()+",";
			}else if("timestamp".equals(attr.getTableType())){
				values += "now(),";
			}else{
				values += "i_"+ attr.getTableName() + ",";
			}
		}
		insert = insert.substring(0,insert.length()-1);
		values = values.substring(0,values.length()-1);
		buffer.append(" insert into "+ SystemConfig.getPgNameSpace()+"."+map.get(ClassUtils.TABLE_NAME)+"(");
		buffer.append(insert).append(")").append("\n");
		buffer.append(" values(" + values +")").append(";\n").append("\n");
		
		buffer.append("exception when others then ").append("\n");
		buffer.append(" o_"+primary.getTableName()+ ":=0").append(";\n");
		buffer.append("end").append("\n").append("$BODY$").append("\n").append(" LANGUAGE 'plpgsql' VOLATILE COST 100").append("\n");
		buffer.append(";").append("\n");
		
		//buffer.append("ALTER FUNCTION "+strHead).append(" OWNER TO \""+SystemConfig.getPgNameSpace()+"\";");
		
		result.put("name", processName);
		result.put("content", buffer.toString());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String,String> createUpdate(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		Map<String,String>result  = new HashMap<String,String>();
		buffer.append("CREATE OR REPLACE FUNCTION ");
		String processName ="\"" + SystemConfig.getPgNameSpace()+"\".\"p_"+map.get(ClassUtils.TABLE_NAME)+"_update";
		List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		String pramiryName = primary.getName();
		String paramsList = "";//存储过程的参数列表
		for(AttributeInfo attr : attributes){
			if("timestamp".equals(attr.getTableType())){
				continue; //时间参数不需要通过代码实现
			}
			paramsList += " IN i_"+attr.getTableName()+ " "+attr.getTableType()+",";
		}
		paramsList+= " OUT o_tagcode text";
		String strHead = processName+"\"" + "("+paramsList+")";
		buffer.append(strHead).append("\n");
		buffer.append(" RETURNS text AS $BODY$").append("\n");
		buffer.append("declare").append("\n");
		
		String aliasList = "";
		String updateSql = "update "+SystemConfig.getPgNameSpace()+"."+map.get(ClassUtils.TABLE_NAME) + " set \n";
		String select = "select ";
		String into = " into ";
		String setString = "";
		
		for(AttributeInfo attr : attributes){
			if( attr.getName().equals(pramiryName)  || "timestamp".equals(attr.getTableType())){
				continue; //设置别名用于update
			}
			aliasList+= " v_"+attr.getTableName()+" "+attr.getTableType()+";\n";
			setString +="if i_"+attr.getTableName() +" is not null then \n"+"  v_"+attr.getTableName()+" := "+"i_"+attr.getTableName()+";\nend if ;\n\n"; 
			updateSql += "  "+attr.getTableName() + "=" + "v_"+attr.getTableName()+",\n";
			select += "t."+attr.getTableName()+",";
			into +=" v_"+attr.getTableName() + ",";
			
		}
		updateSql = updateSql.substring(0,updateSql.length()-2);
		updateSql += "\nwhere " +primary.getTableName()+" = " + "i_"+primary.getTableName()+";";
		buffer.append(aliasList).append("\n").append("begin").append("\n");
		select = select.substring(0,select.length()-1); //去掉最后一个','
		into = into.substring(0,into.length()-1);
		buffer.append(select).append(into).append("\n").append(" from "+SystemConfig.getPgNameSpace()+"."+map.get(ClassUtils.TABLE_NAME) + " t " );
		buffer.append(" where t." +primary.getTableName()+" = " + "i_"+primary.getTableName() + ";").append("\n\n");
		buffer.append("if not found then \n o_tagcode := '02';\n return; end if ;").append("\n\n");
		buffer.append(setString);
		buffer.append(updateSql);
		buffer.append("\n\no_tagcode := '00000000';\nexception when others then \no_tagcode := '01';");
		
		buffer.append("\nend \n$BODY$ \n  LANGUAGE 'plpgsql' VOLATILE COST 100 \n;").append("\n");
		//buffer.append("ALTER FUNCTION "+strHead).append(" OWNER TO \""+SystemConfig.getPgNameSpace()+"\";");

	    result.put("name", processName);
        result.put("content", buffer.toString());
        return result;
	}
	
	private static Map<String,String> createDelete(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		Map<String,String>result  = new HashMap<String,String>();
		String processName ="\"" + SystemConfig.getPgNameSpace()+"\".\"p_"+map.get(ClassUtils.TABLE_NAME)+"_delete";
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		buffer.append("CREATE OR REPLACE FUNCTION ");
		
		String params = "";
		params +=" IN i_"+primary.getTableName()+ " "+primary.getTableType()+",";
		params += "OUT o_tagcode text"; 
		String strHead = processName+"\"" + "("+params+")";
		buffer.append(strHead).append("\n");
		buffer.append(" RETURNS text AS $BODY$").append("\n");
		buffer.append("begin").append("\n");
		buffer.append(" delete from "+SystemConfig.getPgNameSpace()+"."+map.get(ClassUtils.TABLE_NAME));
		buffer.append(" where "+primary.getTableName()+"= i_"+primary.getTableName()+";").append("\n").append("\n");
		buffer.append("  o_tagcode := '00000000';").append("\n");
		buffer.append("  exception when others then ").append("\n");
		buffer.append("  o_tagcode := '01';").append("\n");
		buffer.append("end").append("\n");
		buffer.append("$BODY$").append("\n");
		buffer.append("  LANGUAGE 'plpgsql' VOLATILE COST 100").append("\n;\n");
		//buffer.append("ALTER FUNCTION "+strHead).append(" OWNER TO \""+SystemConfig.getPgNameSpace()+"\";");
		
		result.put("name", processName);
	    result.put("content", buffer.toString());
	    
	    return result;
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String,String> createGet(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		Map<String,String>result  = new HashMap<String,String>();
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		String pramiryName = primary.getName();
		buffer.append("CREATE OR REPLACE FUNCTION ");
		String processName ="\"" + SystemConfig.getPgNameSpace()+"\".\"p_"+map.get(ClassUtils.TABLE_NAME)+"_getby"+primary.getName().toLowerCase();
		List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
		String paramsList = "";//存储过程的参数列表
		String select = " select a.";
		select += primary.getTableName()+",";
		paramsList += "INOUT i_"+primary.getTableName()+" " + primary.getTableType()+",";
		for(AttributeInfo attr : attributes){
			if(attr.getName().equals(pramiryName)){
				continue;
			}
			paramsList += " OUT o_"+attr.getTableName()+ " "+attr.getTableType()+",";
			select += " a."+attr.getTableName() + ",";
		}
		paramsList = paramsList.substring(0,paramsList.length()-1);
		String strHead = processName+"\"" + "("+paramsList+")";
		buffer.append(strHead).append("\n");
		buffer.append(" RETURNS setof record AS $BODY$").append("\n");
		select = select.substring(0,select.length()-1);
		select +=" from " + SystemConfig.getPgNameSpace()+"."+map.get(ClassUtils.TABLE_NAME)+ " a ";
		select += "\nwhere a." +primary.getTableName()+" = " + "i_"+primary.getTableName()+";";
		buffer.append(select).append("\n");
		
		buffer.append("$BODY$ \n  LANGUAGE 'sql' STABLE COST 1 \n;").append("\n").append("\n");
		//buffer.append(" FUNCTION "+strHead).append(" OWNER TO \""+SystemConfig.getPgNameSpace()+"\";");
		
		result.put("name", processName);
        result.put("content", buffer.toString());
        
        return result;
	}
	
	@SuppressWarnings("unchecked")
    private static Map<String,String> createQueryList(Map<String,Object>map){
	    Map<String,String>result  = new HashMap<String,String>();
	    StringBuilder buffer = new StringBuilder();
	  
	    String processName = null;
        List<AttributeInfo>queryList = (List<AttributeInfo>)map.get(ClassUtils.QUERYATTRS);
	    if(queryList.size() == 0){
	       processName = "\"" + SystemConfig.getPgNameSpace()+"\".\"p_"+map.get(ClassUtils.TABLE_NAME)+"_getlist";
	    }else{
	       processName = "\"" + SystemConfig.getPgNameSpace()+"\".\"p_"+map.get(ClassUtils.TABLE_NAME)+"_getlistby";
	       for (AttributeInfo attr : queryList) {
	           processName += attr.getName().toLowerCase()+"and";
	       }
	       processName = processName.substring(0,processName.length()-3);
	    }
        
	    String paramsList = "";
	    if(queryList.size() > 0){
	       for (AttributeInfo attr : queryList) {
	           paramsList += "  IN i_" +attr.getTableName()+ " "+attr.getQueryType()+",\n";
	       }
	       paramsList = paramsList.substring(0,paramsList.length()-2);
	    }
	    
        buffer.append("CREATE OR REPLACE FUNCTION ");
        buffer.append(processName +"\"(\n" +paramsList +"\n)").append("\n");
        
        String returnList = "";
        List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
        for(AttributeInfo attr : attributes){
            returnList += "  "+attr.getTableName()+ " "+ ("timestamp".equals(attr.getQueryType())?" timestamp WITHOUT TIME ZONE":attr.getQueryType()) +",\n";
        }
        returnList = returnList.substring(0,returnList.length() -2);
       /*
        *  返回table 模式 
            buffer.append("RETURNS TABLE").append("\n");
            buffer.append("(\n"+ returnList + "\n)").append("\n");    
        */
        //返回
        buffer.append("RETURNS SETOF ").append(SystemConfig.getPgNameSpace()+"."+map.get(ClassUtils.TABLE_NAME)+"\n");
        
        buffer.append("AS").append("\n").append("$$").append("\n");
        buffer.append("  select a.*"+ " from " + SystemConfig.getPgNameSpace()+"."+map.get(ClassUtils.TABLE_NAME)+ " a ");
        String where = "";
        if(queryList.size() > 0){
            where +="where";
            for (AttributeInfo attr : queryList) {
                where += " a."+attr.getTableName()+"="+"i_" +attr.getTableName()+ " and";
            }
            where = where.substring(0,where.length()-3);
        }
        buffer.append(where +";");
        
        buffer.append("\n$$").append("\n").append("LANGUAGE \"sql\"").append("\n").append("COST 100;").append("\n");
        /*buffer.append("\nALTER FUNCTION " + processName + "\"");
        
        String reType = "";
        if(queryList.size() > 0){
            for (AttributeInfo attr : queryList) {
                reType += attr.getQueryType()+ ",";
            }
            reType = reType.substring(0,reType.length()-1);
        }
        buffer.append("(" + reType+")").append(" OWNER TO \""+SystemConfig.getPgNameSpace()+"\";");*/
        
        result.put("name", processName);
        result.put("content", buffer.toString());
        
        return result;
	}
	
	
}

package com.melot.generator;

import com.melot.conf.SystemConfig;
import com.melot.utils.*;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class MybatisGenerator {

	public static void createMybatisFile(Map<String,Object>map){
		
		String content = getContent(map);
		String filePath = SystemConfig.getFilePath() + File.separator + SystemConfig.getIbatisFilePath().replace(".", File.separator);
		String fileName = map.get(ClassUtils.CLASS_ALIAS) +"Mapper.xml";
		
		FileSystemUtils.writeFile(filePath, fileName, content);
		
	}
	
	private static String getContent(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		//buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n");
		//buffer.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
		//buffer.append("\n");
		//buffer.append( Constants.TAB + Constants.TAB +"<typeAlias alias=\"" + map.get(ClassUtils.CLASS_ALIAS) + "\" type=\""+SystemConfig.getJavaDomainPath() + "." + map.get(ClassUtils.CLASS_NAME) + "\"/>");
		//buffer.append("\n").append("\n");
		
		buffer.append(createAddFunction(map)).append("\n");
		buffer.append(createUpdateFunction(map)).append("\n");
		buffer.append(createDeleteFunction(map)).append("\n");
		buffer.append(createQueryFunction(map)).append("\n");
		buffer.append(createQuereListFunciton(map));
		
		
		//buffer.append("</sqlMap>");
		return buffer.toString();
	}

    /**
     *	把字符串第一个字母转成小写
     */
    private static String changeFirstLowerCase(Object obj) {
        if (obj instanceof String) {
            String str = obj.toString();
            str = str.replace(str.substring(0, 1), str.substring(0, 1).toLowerCase());
            return str;
        }
        return null;
    }
		
	/**
	 *	创建基本新增对象的方法 
	 */
	@SuppressWarnings("unchecked")
	private static String createAddFunction(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		buffer.append(Constants.DOUBLE_TAB +"<select id=\"save"+map.get(ClassUtils.CLASS_NAME)+"\" parameterType=\""+changeFirstLowerCase(map.get(ClassUtils.CLASS_ALIAS))+"\" resultType=\""+ changeFirstLowerCase(primary.getType())+"\">").append("\n");
		buffer.append(Constants.TRIBLE_TAB + "<![CDATA[").append("\n");
		List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
		buffer.append(Constants.QUADRA_TAB + "SELECT * FROM ").append("\n");
		String values = "";
		String pramiryName = primary.getName();
		for(AttributeInfo attr : attributes){
			if(attr.getName().equals(pramiryName) || "timestamp".equals(attr.getTableType())){
				continue; //主键采用数据库自增,不需要传入存储过程
			}
			values += "#{"+attr.getName()+ "},";
		}
		values = values.substring(0,values.length()-1);
		buffer.append(Constants.PENTA_TAB + SystemConfig.getPgNameSpace()+".p_"+map.get(ClassUtils.TABLE_NAME)+"_insert(");
		buffer.append(values).append(")").append("\n");
		buffer.append(Constants.TRIBLE_TAB + "]]>").append("\n");
		buffer.append(Constants.DOUBLE_TAB + "</select>").append("\n");
		return buffer.toString();
	}
	
	/**
	 *	创建基本更新方法 
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static String createUpdateFunction(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		buffer.append(Constants.DOUBLE_TAB +"<select id=\"update"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(primary.getName()) + "\" parameterType=\""+changeFirstLowerCase(map.get(ClassUtils.CLASS_ALIAS))+"\" resultType=\"string\">").append("\n");
		buffer.append(Constants.TRIBLE_TAB + "<![CDATA[").append("\n");
		List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
		buffer.append(Constants.QUADRA_TAB + "SELECT * FROM ").append("\n");
		String values = "";
		for(AttributeInfo attr : attributes){
			if("timestamp".equals(attr.getTableType())){
				continue; //时间参数不需要通过sql更新
			}
			values += "#{"+attr.getName()+ "},";
		}
		values = values.substring(0,values.length()-1);
		buffer.append(Constants.PENTA_TAB + SystemConfig.getPgNameSpace()+".p_"+map.get(ClassUtils.TABLE_NAME)+"_update(");
		buffer.append(values).append(")").append("\n");
		buffer.append(Constants.TRIBLE_TAB + "]]>").append("\n");
		buffer.append(Constants.DOUBLE_TAB + "</select>").append("\n");
		return buffer.toString();
		
	}
	
	/**
	 *	创建基本删除方法 
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static String createDeleteFunction(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		String pramiryName = ((AttributeInfo)map.get(ClassUtils.PRIMARY)).getName();
		List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
		AttributeInfo attribute = null;
		for(AttributeInfo attr : attributes){ //寻找到主键
			if(attr.getName().equals(pramiryName)){
				attribute = attr;
				continue;
			}
			
		}
		buffer.append(Constants.DOUBLE_TAB +"<select id=\"delete"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(pramiryName)+"\" parameterType=\""+ changeFirstLowerCase(attribute.getType()) +"\" resultType=\"string\">").append("\n");
		buffer.append(Constants.TRIBLE_TAB + "<![CDATA[").append("\n");
		buffer.append(Constants.QUADRA_TAB + "SELECT * FROM ").append("\n");
		buffer.append(Constants.PENTA_TAB + SystemConfig.getPgNameSpace()+".p_"+map.get(ClassUtils.TABLE_NAME)+"_delete(");
		if(attribute != null ){
			buffer.append("#{"+ attribute.getName()+"})" ).append("\n");
		}
		buffer.append(Constants.TRIBLE_TAB + "]]>").append("\n");
		buffer.append(Constants.DOUBLE_TAB + "</select>").append("\n");
		return buffer.toString();
	}
	
	
	/**
	 *	创建基本的查询方法 
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static String createQueryFunction(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		String pramiryName = primary.getName();
		buffer.append(Constants.DOUBLE_TAB +"<select id=\"get"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(primary.getName()) + "\" parameterType=\""+changeFirstLowerCase(primary.getType())+"\"")
			.append(" resultType=\""+changeFirstLowerCase(map.get(ClassUtils.CLASS_ALIAS))+"\">").append("\n");
		buffer.append(Constants.TRIBLE_TAB + "<![CDATA[").append("\n");
		List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
		String select = "";
		select += Constants.PENTA_TAB + "i_"+primary.getTableName() + " as " + primary.getName() + "," + "\n" ;
		for(AttributeInfo attr : attributes){
			if(attr.getName().equals(pramiryName)){
				continue;
			}
			select += Constants.PENTA_TAB + "o_"+attr.getTableName() + " as " + attr.getName() + "," + "\n" ; 
		}
		buffer.append(Constants.QUADRA_TAB + "SELECT ").append("\n").append(select.substring(0,select.length()-2)).append("\n")
		.append(Constants.QUADRA_TAB + "FROM ").append("\n");
		buffer.append(Constants.PENTA_TAB + SystemConfig.getPgNameSpace()+".p_"+map.get(ClassUtils.TABLE_NAME)+"_getby"+primary.getName().toLowerCase()+"(");
		buffer.append( "#{"+ primary.getName()+"}" ).append(")\n");
		buffer.append(Constants.TRIBLE_TAB + "]]>").append("\n");
		buffer.append(Constants.DOUBLE_TAB + "</select>").append("\n");
		
		return buffer.toString();
	}
	
	 @SuppressWarnings("unchecked")
	private static String createQuereListFunciton(Map<String,Object>map){
	    StringBuilder buffer = new StringBuilder();
	    List<AttributeInfo>queryList = (List<AttributeInfo>)map.get(ClassUtils.QUERYATTRS);
	    String methodName = "";  //java 方法名
	    String processName = ""; //存储过程名
	    String params = "";
	    if(queryList.size() == 0){
	        methodName =  "get" + map.get(ClassUtils.CLASS_NAME) + "s";
	        processName =  SystemConfig.getPgNameSpace()+".p_"+map.get(ClassUtils.TABLE_NAME)+"_getlist";
        }else{
            processName =  SystemConfig.getPgNameSpace()+".p_"+map.get(ClassUtils.TABLE_NAME)+"_getlistby";
            for(AttributeInfo attr : queryList){
                methodName += StringUtils.getFirstHighName(attr.getName()) + "And";
                processName += attr.getName().toLowerCase()+"and";
                params += "#{"+attr.getName()+"},";
            }
            methodName = methodName.substring(0,methodName.length() -3 );
            methodName =  "get" + map.get(ClassUtils.CLASS_NAME) + "sBy" + methodName;
            processName = processName.substring(0,processName.length()-3);
            params = params.substring(0,params.length() -1 );
        }
	    
	    if(queryList.size() == 0){
	        buffer.append(Constants.DOUBLE_TAB +"<select id=\""+ methodName +"\"") .append(" resultType=\""+map.get(ClassUtils.CLASS_ALIAS)+"\">").append("\n");
	    }else{
	        buffer.append(Constants.DOUBLE_TAB +"<select id=\""+methodName + "\"")
            .append(" resultType=\""+changeFirstLowerCase(map.get(ClassUtils.CLASS_ALIAS))+"\">").append("\n");
	    }
	    buffer.append(Constants.TRIBLE_TAB + "<![CDATA[").append("\n");
	    buffer.append(Constants.QUADRA_TAB + "SELECT ").append("\n");
	    
	    String select = "";
	   
        List<AttributeInfo>attributes = (List<AttributeInfo>)map.get(ClassUtils.ATTRIBUTES);
	    for(AttributeInfo attr : attributes){
            select += Constants.PENTA_TAB +attr.getTableName() + " as " + attr.getName() + "," + "\n" ; 
        }
	    
	    buffer.append(select.substring(0,select.length()-2)).append("\n");
	    buffer.append(Constants.QUADRA_TAB +"FROM\n" + Constants.PENTA_TAB + processName + "(" + params + ")").append("\n");
	    buffer.append(Constants.TRIBLE_TAB + "]]>").append("\n");
	    buffer.append(Constants.DOUBLE_TAB + "</select>").append("\n");
	    
	    return buffer.toString();
	    
	}
	
	
	
}

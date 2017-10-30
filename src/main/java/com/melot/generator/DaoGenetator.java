package com.melot.generator;

import com.melot.conf.SystemConfig;
import com.melot.utils.*;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class DaoGenetator {
	
	
	public static void createDaoFile(Map<String,Object>map){
		createDao(map);
		//createDaoImpl(map);
		
	}
	
	/**
	 *	创建Dao文件 
	 */
	private static void createDao(Map<String,Object>map){
		String filePath = SystemConfig.getFilePath() + File.separator + SystemConfig.javaDaoPath().replace(".", File.separator);
		filePath +=  File.separator +"dao";
		String fileName = map.get(ClassUtils.CLASS_NAME) +"Dao.java";
		String content = getDaoContent(map);
		FileSystemUtils.writeFile(filePath, fileName, content);
	}
	
	
	/**
	 *	创建DaoImpl 
	 */
	private static void createDaoImpl(Map<String,Object>map){
		String filePath = SystemConfig.getFilePath() + File.separator + SystemConfig.javaDaoPath().replace(".", File.separator);
		filePath +=  File.separator +"dao" +  File.separator + "impl";
		String fileName = map.get(ClassUtils.CLASS_NAME) +"DaoImpl.java";
		String content = getDaoImplContent(map);
		FileSystemUtils.writeFile(filePath, fileName, content);
	}
	
	
	/**
	 *	具体写interface文件 
	 */
	private static String getDaoContent(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		String className =  (String)map.get(ClassUtils.CLASS_NAME);
		buffer.append("package " + SystemConfig.javaDaoPath()+".dao;").append("\n").append("\n");
		buffer.append("import " + SystemConfig.getJavaDomainPath() +"." + className).append(";\n").append("\n");
		buffer.append("public interface " + className+ "Dao {").append("\n").append("\n");
		
		buffer.append(Constants.TAB + getSaveMethod(map)).append(";\n").append("\n");
		buffer.append(Constants.TAB + getUpdateMethod(map)).append(";\n").append("\n");
		buffer.append(Constants.TAB + getDeleteMethod(map)).append(";\n").append("\n");
		buffer.append(Constants.TAB + getQueryMethod(map)).append(";\n").append("\n");
		buffer.append(Constants.TAB + getQueryListMethod(map)).append(";\n");
		buffer.append("}");
		return buffer.toString();
	}

	/**
	 * 具体写实现类文件
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static String getDaoImplContent(Map<String,Object>map){
		StringBuilder buffer = new StringBuilder();
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		String className =  (String)map.get(ClassUtils.CLASS_NAME);
		buffer.append("package " +  SystemConfig.javaDaoPath()+".dao.impl;").append("\n").append("\n");
		buffer.append("import " + SystemConfig.javaDaoPath()+".dao."+ className).append("Dao;\n");
		buffer.append("import " + SystemConfig.javaDaoPath()+"."+ "base.BaseDao").append(";\n").append("\n");
		buffer.append("import " + SystemConfig.getJavaDomainPath() +"." + className).append(";\n\n");

		buffer.append("public class " + className+ "DaoImpl extends BaseDao implements " + className + "Dao {").append("\n").append("\n");
		
		String template ="sqlMapClientTemplate";
		buffer.append(Constants.TAB +"@Override").append("\n");
		buffer.append(Constants.TAB +"public "+ getSaveMethod(map)).append("{\n");
		buffer.append(Constants.DOUBLE_TAB + "return ("+ primary.getType() +")"+template+".queryForObject(\""+map.get(ClassUtils.CLASS_ALIAS)+"."+"save"+className+"\","+map.get(ClassUtils.CLASS_ALIAS)+");").append("\n");
		buffer.append(Constants.TAB +"}").append("\n").append("\n");
		
		buffer.append(Constants.TAB +"@Override").append("\n");
		buffer.append(Constants.TAB +"public "+ getUpdateMethod(map)).append("{\n");
		buffer.append(Constants.DOUBLE_TAB  + "String tagCode = (String)"+ template+".queryForObject(\""+map.get(ClassUtils.CLASS_ALIAS)+"."+"update"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(primary.getName())+"\","+map.get(ClassUtils.CLASS_ALIAS)+");").append("\n");
		buffer.append(Constants.DOUBLE_TAB +"return successFlag(tagCode);").append("\n");
		buffer.append(Constants.TAB +"}").append("\n").append("\n");
		
		buffer.append(Constants.TAB +"@Override").append("\n");
		buffer.append(Constants.TAB +"public "+ getDeleteMethod(map)).append("{\n");
		buffer.append(Constants.DOUBLE_TAB + "String tagCode = (String)"+ template+".queryForObject(\""+map.get(ClassUtils.CLASS_ALIAS)+"."+"delete"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(primary.getName())+"\"," + primary.getName() +");").append("\n");
		buffer.append(Constants.DOUBLE_TAB +"return successFlag(tagCode);").append("\n");
		buffer.append(Constants.TAB +"}").append("\n").append("\n");
		
		buffer.append(Constants.TAB +"@Override").append("\n");
		buffer.append(Constants.TAB +"public "+ getQueryMethod(map)).append("{\n");
		buffer.append(Constants.DOUBLE_TAB + "return ("+className+")"+template+".queryForObject(\""+map.get(ClassUtils.CLASS_ALIAS)+"."+"get"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(primary.getName())+"\"," + primary.getName() +");").append("\n");
		buffer.append(Constants.TAB +"}").append("\n").append("\n");
		
		buffer.append(Constants.TAB +"@Override\n");
		buffer.append(Constants.TAB +"@SuppressWarnings(\"unchecked\")\n");
        buffer.append(Constants.TAB +"public "+ getQueryListMethod(map)).append("{\n");
        
        buffer.append(Constants.DOUBLE_TAB +"Map<String, Object> map = new HashMap<String, Object>();\n");
        List<AttributeInfo>queryList = (List<AttributeInfo>)map.get(ClassUtils.QUERYATTRS);
        String names = "";
        String mapString = "";
        if(queryList.size() > 0){
            for(AttributeInfo attr : queryList){
                names += StringUtils.getFirstHighName(attr.getName()) + "And";
                mapString += Constants.DOUBLE_TAB + "map.put(\""+attr.getName()+"\","+attr.getName()+");\n";
            }
            names = names.substring(0,names.length() -3 );
        }
        if(queryList.size() == 0){
            buffer.append(Constants.DOUBLE_TAB + "return ("+"List<"+map.get(ClassUtils.CLASS_NAME)+">)"+template+".queryForList(\""+map.get(ClassUtils.CLASS_ALIAS)+"."+"get"+map.get(ClassUtils.CLASS_NAME)+"s"+"\"" +");").append("\n");
        }else{
            buffer.append(mapString);
            buffer.append(Constants.DOUBLE_TAB + "return ("+"List<"+map.get(ClassUtils.CLASS_NAME)+">)"+template+".queryForList(\""+map.get(ClassUtils.CLASS_ALIAS)+"."+"get"+map.get(ClassUtils.CLASS_NAME)+"sBy"+names+"\"," + "map" +");").append("\n");
        }
        buffer.append(Constants.TAB +"}").append("\n");
		
		buffer.append("}");
		return buffer.toString();
	}

	private static String getSaveMethod(Map<String,Object>map){
		String className =  (String)map.get(ClassUtils.CLASS_NAME);
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		return primary.getType()+" save"+className +"("+ className + " " + map.get(ClassUtils.CLASS_ALIAS)  +")";
	}
	
	private static String getUpdateMethod(Map<String,Object>map){
		String className =  (String)map.get(ClassUtils.CLASS_NAME);
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		return "String update"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(primary.getName())
				+ "("+ className + " " + map.get(ClassUtils.CLASS_ALIAS)  +")";
	}
	
	private static String getDeleteMethod(Map<String,Object>map){
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		return "String delete"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(primary.getName())
				+ "("+ primary.getType() + " " + primary.getName() +")";
	}
	
	private static String getQueryMethod(Map<String,Object>map){
		AttributeInfo primary = (AttributeInfo)map.get(ClassUtils.PRIMARY);
		return map.get(ClassUtils.CLASS_NAME) + " get"+map.get(ClassUtils.CLASS_NAME)+"By"+StringUtils.getFirstHighName(primary.getName())
				+ "("+ primary.getType() + " " + primary.getName() +")";
	}
	
	@SuppressWarnings("unchecked")
	private static String getQueryListMethod(Map<String,Object>map){
        List<AttributeInfo>queryList = (List<AttributeInfo>)map.get(ClassUtils.QUERYATTRS);
	    String names = "";
	    String params = "";
	    if(queryList.size() == 0){
	        return "List<"+map.get(ClassUtils.CLASS_NAME)+"> get" + map.get(ClassUtils.CLASS_NAME) + "s()";
	    }else{
	        for(AttributeInfo attr : queryList){
	            names += StringUtils.getFirstHighName(attr.getName()) + "And";
	            params += "@Param(\"" + attr.getName() +"\") " + attr.getType()+" "+ attr.getName()+", ";
	        }
	        names = names.substring(0,names.length() -3 );
	        params =params.substring(0,params.length() -2 );
	        return "List<"+map.get(ClassUtils.CLASS_NAME)+"> get" + map.get(ClassUtils.CLASS_NAME) + "sBy" + names+"(" + params +")";
	    }
	}
	
	
}

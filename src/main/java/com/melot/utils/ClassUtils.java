package com.melot.utils;

import com.melot.annotation.Column;
import com.melot.annotation.Id;
import com.melot.annotation.Query;
import com.melot.annotation.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClassUtils {
	
	public static final String CLASS_NAME = "className";
	public static final String CLASS_ALIAS = "classAlias";
	public static final String TABLE_NAME = "tableName";
	public static final String PRIMARY =  "primary";
	public static final String ATTRIBUTES =  "attributes";
	public static final String QUERYATTRS = "queryAttr";
	
	/** Suffix for array class names: "[]" */
	public static final String ARRAY_SUFFIX = "[]";

	/** Prefix for internal array class names: "[" */
//	private static final String INTERNAL_ARRAY_PREFIX = "[";

	/** Prefix for internal non-primitive array class names: "[L" */
	//private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";

	/** The package separator character '.' */
	private static final char PACKAGE_SEPARATOR = '.';

	/** The inner class separator character '$' */
	private static final char INNER_CLASS_SEPARATOR = '$';

	/** The CGLIB class separator character "$$" */
	public static final String CGLIB_CLASS_SEPARATOR = "$$";

	/** The ".class" file suffix */
	public static final String CLASS_FILE_SUFFIX = ".class";

	
	/**
	 * Get the class name without the qualified package name.
	 * @param className the className to get the short name for
	 * @return the class name of the class without the package name
	 * @throws IllegalArgumentException if the className is empty
	 */
	public static String getShortName(String className) {
		int lastDotIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
		int nameEndIndex = className.indexOf(CGLIB_CLASS_SEPARATOR);
		if (nameEndIndex == -1) {
			nameEndIndex = className.length();
		}
		String shortName = className.substring(lastDotIndex + 1, nameEndIndex);
		shortName = shortName.replace(INNER_CLASS_SEPARATOR, PACKAGE_SEPARATOR);
		return shortName;
	}
	
	/**
	 * Get the class name without the qualified package name.
	 * @param clazz the class to get the short name for
	 * @return the class name of the class without the package name
	 */
	public static String getShortName(Class<?> clazz) {
		return getShortName(getQualifiedName(clazz));
	}
	
	/**
	 * Return the qualified name of the given class: usually simply
	 * the class name, but component type class name + "[]" for arrays.
	 * @param clazz the class
	 * @return the qualified name of the class
	 */
	public static String getQualifiedName(Class<?> clazz) {
		if (clazz.isArray()) {
			return getQualifiedNameForArray(clazz);
		}
		else {
			return clazz.getName();
		}
	}
	
	
	/**
	 * Build a nice qualified name for an array:
	 * component type class name + "[]".
	 * @param clazz the array class
	 * @return a qualified name for the array class
	 */
	private static String getQualifiedNameForArray(Class<?> clazz) {
		StringBuilder result = new StringBuilder();
		while (clazz.isArray()) {
			clazz = clazz.getComponentType();
			result.append(ClassUtils.ARRAY_SUFFIX);
		}
		result.insert(0, clazz.getName());
		return result.toString();
	}
	
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back to system class loader...
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = ClassUtils.class.getClassLoader();
		}
		return cl;
	}

	
	
	/**
	 * 解析一个java类,用于后续的属性文件生成
	 * 获取的属性有对应的类名,表名,属性组 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object>parseClass(Class<?>cla){
		Map<String,Object>result = new HashMap<String,Object>();
		String shortClassName = getShortName(cla.getName());
		result.put( CLASS_NAME , shortClassName);
		result.put( CLASS_ALIAS , StringUtils.getFirstLowName(shortClassName));
		result.put(QUERYATTRS, new ArrayList<AttributeInfo>());
		
		List<AttributeInfo>attributes = new ArrayList<AttributeInfo>();
		
		Table table = cla.getAnnotation(Table.class); //解析获取数据库中的表名
		if(table != null && !StringUtils.isEmpty(table.name())){
			result.put(TABLE_NAME, table.name());
		}else{
			result.put(TABLE_NAME, StringUtils.toUnderlineStyle(shortClassName) );
		}
		
		
		Field fields[] = cla.getDeclaredFields();
		for(Field field : fields){
			
			if(field.getName().equals("serialVersionUID")){
				continue; //序列id则不需要
			}
			
			Column column = field.getAnnotation(Column.class);
			if(column == null){
			    continue;
			}
			
			String fieldName = field.getName(); //属性名称
			Class<?>fieldType = field.getType();  //属性的声明类型
			if(fieldType.isArray()){
				continue;  //数组不作为数据库bean属性看待
			}
			String typeName = fieldType.isPrimitive() ? fieldType.getName() :getShortName(fieldType.getName()); //获取属性类型
			String tableName= null;
			tableName = column == null || "".equals(column.name()) ? StringUtils.toUnderlineStyle(fieldName) : column.name();
			
			AttributeInfo attibute = new AttributeInfo(fieldName,typeName, tableName);
			attibute.setTableType(column.type());
			
			attributes.add(attibute);
			Id id= field.getAnnotation(Id.class);
			if(id != null){ //解析到该属性为主键
				result.put(PRIMARY,attibute);
			}
			
			Query query = field.getAnnotation(Query.class);
			if(query != null){
                List<AttributeInfo>list =  (List<AttributeInfo>)result.get(QUERYATTRS);
			    list.add(attibute);
			}
		}
		result.put(ATTRIBUTES, attributes);
		if(result.get(PRIMARY) == null){
			throw new IllegalArgumentException("primary is requested");
		}
		return result;
	} 
	
	
}

package com.melot.utils;

public class AttributeInfo {
	
	private String name;
	private String type;
	private String tableName;
	private String tableType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getQueryType() {
	    if("int4".equals(tableType)){
	        return "integer";
	    }else{
	        return tableType;
	    }
    }
	
    public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public AttributeInfo(String name, String type, String tableName) {
		super();
		this.name = name;
		this.type = type;
		this.tableName = tableName;
	}

}

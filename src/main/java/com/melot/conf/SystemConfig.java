package com.melot.conf;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;


public abstract class SystemConfig {
	private static final Logger logger = Logger.getLogger(SystemConfig.class);

	private static Properties pp;
	private static String filePath;
	private static String javaDaoPath;
	private static String javaDomainPath;
	private static String ibatisFilePath;
	private static String pgNameSpace;
	
	
	private SystemConfig() {
	}

	static {
		reload();
	}
	public static void reload() {
		pp = new Properties();
		InputStream ins = null;
		try {
			ins = SystemConfig.class.getClassLoader().getResourceAsStream(
					"system.properties");
			logger.info("system config file load begin");
			pp.load(ins);
			filePath = getStringProperty("file.path", "/Users/shengjian/Downloads/pgsql");
			javaDaoPath = getStringProperty("base.java.dao.path", "com.bryan");
			javaDomainPath = getStringProperty("base.jave.domain.path", "com.bryan");
			ibatisFilePath = getStringProperty("base.mybatis.file.path", "mybatis.map");
			pgNameSpace = getStringProperty("pg.nameSpace", "tshow");
		} catch (Exception e) {
			logger.error("SystemConfig.Load.Error", e);
		}
	}

	private static String getStringProperty(String key, String defaultValue) {
		if (pp == null || pp.getProperty(key) == null) {
			return defaultValue;
		}
		return pp.getProperty(key).trim();
	}


	public static String getFilePath() {
		return filePath;
	}

	public static String javaDaoPath() {
		return javaDaoPath;
	}

	public static String getIbatisFilePath() {
		return ibatisFilePath;
	}

	public static String getPgNameSpace() {
		return pgNameSpace;
	}

	public static String getJavaDomainPath() {
		return javaDomainPath;
	}


}

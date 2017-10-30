package com.melot.utils;

import com.melot.generator.DaoGenetator;
import com.melot.generator.MybatisGenerator;
import com.melot.generator.PgGenerator;

import java.util.Map;

public abstract class GeneratorUtils {
	
	public static void genarator(Map<String,Object>map){
		
		MybatisGenerator.createMybatisFile(map);
		
		DaoGenetator.createDaoFile(map);
	
		PgGenerator.createPgFile(map);	
		
	}

}

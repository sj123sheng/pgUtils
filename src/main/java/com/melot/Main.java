/**
 * Copyright 2012-2014 the original author or authors.
 */
package com.melot;

import java.util.List;
import java.util.Map;

import com.melot.utils.ClassUtils;
import com.melot.utils.GeneratorUtils;
import com.melot.utils.ScanUtils;

/**
 * @author guoping.yao@melot.com
 *
 */
public class Main {
	
	
	
	public static void main(String[] args) {
		List<Class<?>> list = ScanUtils.scanAndgetAllClasses("com.melot.entity");
		for(Class<?>cls : list){
			Map<String,Object>map = ClassUtils.parseClass(cls);
			GeneratorUtils.genarator(map);  //生成对应文件
		}
		System.out.println("create successfully");
	}

}

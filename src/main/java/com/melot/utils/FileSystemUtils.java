/**
 * Copyright 2012-2014 the original author or authors.
 */
package com.melot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

/**
 * @author guoping.yao@melot.com
 *
 */
public abstract class FileSystemUtils {

	private static Logger logger = Logger.getLogger(FileSystemUtils.class);
	
	public static void writeFile(String filePath,String fileName,String fileContent){
		
		String path = filePath.endsWith(File.separator) ? filePath : filePath + File.separator;
		File folder = new File(path);
		if (!folder.exists() && !folder.isDirectory()) { 
			if(folder.mkdirs()){
				logger.debug("create folder success");
			}else{
				return;
			}
		}
		try {
			File file = new File(path+fileName);
			OutputStreamWriter ff =new OutputStreamWriter(new FileOutputStream(file));
			ff.write(fileContent);
			ff.close();
			
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public static boolean deleteRecursively(File root) {
		if (root != null && root.exists()) {
			if (root.isDirectory()) {
				File[] children = root.listFiles();
				if (children != null) {
					for (File child : children) {
						deleteRecursively(child);
					}
				}
			}
			return root.delete();
		}
		return false;
	}

	public static void copyRecursively(File src, File dest) throws IOException {
		doCopyRecursively(src, dest);
	}

	private static void doCopyRecursively(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			dest.mkdir();
			File[] entries = src.listFiles();
			if (entries == null) {
				throw new IOException("Could not list files in directory: " + src);
			}
			for (File entry : entries) {
				doCopyRecursively(entry, new File(dest, entry.getName()));
			}
		}
		else if (src.isFile()) {
			try {
				dest.createNewFile();
			}
			catch (IOException ex) {
				IOException ioex = new IOException("Failed to create file: " + dest);
				ioex.initCause(ex);
				throw ioex;
			}
			FileCopyUtils.copy(src, dest);
		}
	}
}

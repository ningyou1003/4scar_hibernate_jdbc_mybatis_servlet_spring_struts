package com.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {
	public static void toFile(String path,String fileName,String content){
		//保存到文件里
		try {
			File file = 
		    new File(path+"\\" + fileName+".java");
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.getBytes("UTF-8"));
			fos.flush();
			fos.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}

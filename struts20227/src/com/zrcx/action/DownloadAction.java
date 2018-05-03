package com.zrcx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;//版本号，
	
	private String fileName;//待下载的文件名
	private InputStream downloadFile;//待下载的文件流
	
	@Override
	public String execute() throws Exception {
		System.out.println("------文件下载------");
		
		return SUCCESS;
	}
	public InputStream getDownloadFile() {
		fileName = "User - 副本.java";
		File file = new File("d:\\temp\\"+fileName);
		FileInputStream fis = null;
		
		try {
			//对文件名进行编码处理
			this.setFileName(URLEncoder.encode(fileName, "UTF-8"));
			fis = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fis;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

	
	
}

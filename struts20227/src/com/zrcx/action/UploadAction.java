package com.zrcx.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;//版本号，
	
	private String name;
	private File image;
	private String imageContentType;
	private String imageFileName;
	
	@Override
	public String execute() throws Exception {
		System.out.println("------文件上传------");
		System.out.println("名字：" + name);
		System.out.println("文件类型：" + imageContentType);
		System.out.println("文件名：" + imageFileName);
		File file = new File("D:\\temp\\"+imageFileName);//保存上传文件
		if(!file.exists()){
			file.createNewFile();
		}
		FileInputStream fis = new FileInputStream(image);
		FileOutputStream fos = new FileOutputStream(file);
		byte[] b = new byte[1024];
		int i = 0;
		while((i=fis.read(b))!=-1){
			fos.write(b, 0, i);
			fos.flush();
		}
		fis.close();
		fos.close();
		System.out.println("文件上传完毕了");
		return "index";

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
}

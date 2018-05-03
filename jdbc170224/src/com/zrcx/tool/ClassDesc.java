package com.zrcx.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
/**
 * java类的描述类
 * @author ZHQL
 */
public class ClassDesc {
	private String path;//类文件保存
	private String pkg;//包名
	private String name;//类名
	//字段描述
	private List<FieldDesc> fields;
	//表注释(key-序列号,value-注释)
	private Map<Integer,String> comments;
	
	public void print(){
		//
		StringBuilder s1 = new StringBuilder();
		s1.append("package ").append(pkg).append(";\n\n");
		//
		StringBuilder s2 = new StringBuilder();
		s2.append("public class ").append(name).append(" {\n");
		StringBuilder s3 = new StringBuilder();
		//循环所有字段
		for (FieldDesc fd : this.fields) {
			//生成注释
			s2.append("     //")
			.append(this.getComment(fd.getCol()))
			.append("\n");
			//生成属性
			s2.append("     private ");
			if("Date".equals(fd.getType())){
				//s1.append("import java.util.Date;\n\n");
				s2.append(fd.getType()).append(" ");
			}else{			
				s2.append(fd.getType()).append(" ");
			}
			s2.append(fd.getName()).append(";\n");
			//生成setter getter方法
			String name1 = fd.getName();
			//属性首字母大写
			String name2 = name1.substring(0,1).toUpperCase()
					+ name1.substring(1);
			//生成set方法
			s3.append("\n     public void")
			.append(" set").append(name2)
			.append("(").append(fd.getType())
			.append(" ").append( name1 ).append("){\n")
			.append("\tthis.").append(name1)
			.append("=").append(name1).append(";\n")
			.append("     }\n");
			//生成get方法
			s3.append("\n     public ").append(fd.getType())
			.append(" get").append(name2)
			.append("(").append("){\n")
			.append("\treturn ").append(name1).append(";\n")
			.append("     }\n");
		}
		s2.append(s3);//
		s2.append("}\n");
		System.out.println(s1.toString() + s2.toString());
		//保存到文件里
		try {
			File file = 
		    new File(this.path+"\\" + this.name+".java");
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write((s1.toString() + s2.toString()).getBytes("UTF-8"));
			fos.flush();
			fos.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}		
    }	
	/**
	 * 根据数据库字段名获取对应注释
	 * @return
	 */
	public String getComment(Integer id) {
		return comments.get(id);
	}

	public void setComments(Map<Integer, String> comments) {
		this.comments = comments;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FieldDesc> getFields() {
		return fields;
	}
	public void setFields(List<FieldDesc> fields) {
		this.fields = fields;
	}
}

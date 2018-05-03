package com.zrcx.tool;
/**
 * 数据库表字段描述类
 * @author ZHQL
 */
public class FieldDesc {
	private int col;//列序号
	private String name;//列名
	private String type;//列类型
	private int precision;//类型长度
	private int scale;//小数位数
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	//根据数据库字段返回java属性名称
	public String getName() {
		String[] s = this.name.toLowerCase().split("_");
		if(s.length==1){
			return name.toLowerCase();
		}else{
			StringBuilder s1 = new StringBuilder();
			s1.append(s[0]);//第一部分
			for (int i = 1; i < s.length; i++) {
				//后面每部分首字母大写
				s1.append(s[i].substring(0, 1).toUpperCase());
				//
				s1.append(s[i].substring(1));
			}
			return s1.toString();
		}
	}	
	public void setName(String name) {
		this.name = name;
	}
	//根据数据库字段返回java属性类型
	public String getType() {
		if("NUMBER".equalsIgnoreCase(type)){//数值型
			if(this.scale==0){
				if(this.precision<=6){
					return "int";
				}else{
					return "long";
				}
			}else{
				if(this.precision<=8){
					return "float";
				}else{
					return "double";
				}				
			}
		}else if("DATE".equalsIgnoreCase(type)){
			return "Date";
		}else{
			return "String";
		}
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
}

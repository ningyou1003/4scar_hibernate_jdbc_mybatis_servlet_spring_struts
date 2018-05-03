package com;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import com.util.DataBaseType;
import com.util.DbconnUtil;
import com.util.StringUtil;
/**
 * 数据库工具类
 * @author zhql
 * @data:2016-10-29
 */
public class Table2Pojo {

    public static final String LINE = "\r\n";//换行符
    public static final String TAB = "\t";//制表符
    //数据库连接配置
    public static final String dburl = "127.0.0.1:1521";
    public static final String dbname = "XE";
    public static final String user = "u1618";
    public static final String name = "u1618";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    	//表名称
    	String tableName = "T_STUDENT";        
    	Connection con = getConnection();
        table2pojo(con, tableName.toUpperCase(), DbconnUtil.ORACLE, "" , true);
    }

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
       return DbconnUtil.getConnection(dburl, dbname, user, name, DbconnUtil.ORACLE);
    }


    /**
     * 数据库表生成相应的java类，生成规则
     * 类名=表名（第一个字母大写）
     * 属性名=数据库列名
     * get/set方法 = 根据标准生成
     * 其中生成的基本类型均为包装类，例如Integer,Long,Boolean,String,Date 
     * @param connection
     * @param tableName
     * @param dbType
     * @param path
     * @param isCreateFile
     * @return
     * @throws SQLException
     */
    public static String table2pojo(Connection connection, String tableName,
            int dbType, String path , boolean isCreateFile) throws SQLException {
    	//读取到字段注释
    	Map<String,String> commentMap = DataBaseType.getColumnComment(tableName, connection);
        String sql = "select * from " + tableName + " where 1 <> 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = connection.prepareStatement(sql);
        rs = ps.executeQuery();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        
        StringBuffer sb = new StringBuffer();
        tableName = tableName.substring(0, 1).toUpperCase() 
        		   +tableName.subSequence(1, tableName.length());
        sb.append("public class " + StringUtil.getClassName(tableName) + " {");
        sb.append(LINE);
        String fieldType = "";
        String fieldName = "";
        int precision = 0;
        int scale = 0;
        //字段名
        for (int i = 1; i <= columnCount; i++) {
        	fieldType = md.getColumnTypeName(i);
        	scale = md.getScale(i);
        	precision = md.getPrecision(i);
        	fieldName = md.getColumnName(i);
        	//System.out.println("类型长度:" + precision);
        	//System.out.println("小数点位数:" + scale);
            sb.append(TAB);
            sb.append("//").append(commentMap.get(fieldName));//注释
            sb.append(LINE);
            sb.append(TAB);
            sb.append("private " + DataBaseType.getPojoType(fieldType,precision,scale) + " "
                    + StringUtil.getFeildName(fieldName) + ";");
            sb.append(LINE);
        }
        //字段的SET，GET方法
        for (int i = 1; i <= columnCount; i++) {
        	fieldType = md.getColumnTypeName(i);
        	precision = md.getPrecision(i);
        	fieldName = md.getColumnName(i);
            sb.append(TAB);
            String pojoType = DataBaseType.getPojoType(fieldType,precision,scale);
            String columnName = StringUtil.getFeildName(md.getColumnName(i));
            String getName = null;
            String setName = null;
            if (columnName.length() > 1) {
                getName = "public "+pojoType+" get" + columnName.substring(0, 1).toUpperCase()
                        + columnName.substring(1, columnName.length()) + "() {";
                setName = "public void set" + columnName.substring(0, 1).toUpperCase()
                        + columnName.substring(1, columnName.length()) + "("
                        + pojoType + " " + columnName + ") {";
            } else {
                getName = "public get" + columnName.toUpperCase() + "() {";
                setName = "public set" + columnName.toUpperCase() + "(" + pojoType
                        + " " + columnName + ") {";
            }
            sb.append(LINE).append(TAB).append(getName);
            sb.append(LINE).append(TAB).append(TAB);
            sb.append("return " + columnName +";");
            sb.append(LINE).append(TAB).append("}");
            sb.append(LINE);
            sb.append(LINE).append(TAB).append(setName);
            sb.append(LINE).append(TAB).append(TAB);
            sb.append("this." +  columnName + " = " + columnName +";" );
            sb.append(LINE).append(TAB).append("}");
            sb.append(LINE);            
        }
        sb.append("}");
        System.out.println(sb.toString());
        
//        if(isCreateFile){
//            FileUtils.stringToFile(null,tableName +".java" , sb.toString());
//        }
        return null;
    }

}

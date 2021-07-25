package com.jizhong.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC工具类
 * 1. 获取数据库连接对象
 * 2. 关闭连接，释放资源
 */
public class JDBCUtils {
	private static String url;
	private static String user;
	private static String password;
	private static String driver;
	
	//静态代码块：会随着类的加载而执行
	static {
		try{
			Properties pro = new Properties();//读取properties文件
			InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			pro.load(inputStream);
			//get(String key)：通过key获取value
			url = (String)pro.get("url");
			user = (String)pro.get("user");
			password = (String)pro.get("password");
			driver = (String)pro.get("driver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//1. 获取数据库连接对象
	public static Connection getConnection() throws Exception{
		Class.forName(driver);
		Connection c = DriverManager.getConnection(url,user,password);
		return c;
	}
	
	//2. 关闭连接，释放资源
	public static void close(ResultSet resultSet,Statement statement,Connection connection){
		try{
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}

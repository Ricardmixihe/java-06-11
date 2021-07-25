package com.jizhong.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * Druid连接池
 */
public class DruidDataSource {
	public static void main(String[] args) throws Exception {
		//1. 加载Properties配置文件
		Properties pro = new Properties();
		//2. 通过字节码对象拿到ClassLoader类加载器对象，通过类加载器对象的getResourceAsStream()
		//getResourceAsStream(文件位置)：获取指定文件输入流对象
		InputStream input = DruidDataSource.class.getClassLoader().getResourceAsStream("druid.properties");
		//3. 将输入流对象加载进Properties对象中
		pro.load(input);
		
		//4. 创建Druid连接池的核心对象
		//DruidDataSourceFactory：工厂类，可以通过工厂类创建出连接池对象
		DataSource dataSource =  DruidDataSourceFactory.createDataSource(pro);
		//5. getConnection()：获取连接，从连接池中拿到连接
		for(int i = 0;i < 11;i++){
			Connection connection = dataSource.getConnection();
			System.out.println(i + "------" + connection);
			//归还连接
			connection.close();
		}
	}
}

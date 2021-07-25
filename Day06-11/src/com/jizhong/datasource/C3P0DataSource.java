package com.jizhong.datasource;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0连接池
 */
public class C3P0DataSource {
	public static void main(String[] args) throws SQLException {
		//1. 创建C3P0核心对象
		DataSource dataSource = new ComboPooledDataSource();
		//验证连接池最大连接数量
		for(int i = 0;i < 11;i++){
			//2. 从连接池中拿到连接对象
			Connection connection = dataSource.getConnection();
			System.out.println(i + "-----" + connection);
			
			//3. 归还连接
			if (i == 5) {
				connection.close();
			}
			
		}
	}
}

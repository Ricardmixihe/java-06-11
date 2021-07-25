package com.jizhong.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.jizhong.utils.JDBCUtils;

/**
 * 登录
 */
public class Login {
	public static void main(String[] args) {
		//1. 控制台输入用户名、密码
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String username = scanner.next();
		System.out.println("请输入密码：");
		String password = scanner.next();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		//2. 获取数据库数据，判断用户名和密码是否正确（JDBC）
		try{
			//2.1 获取连接
			connection = JDBCUtils.getConnection();
			//2.2 创建statement执行静态SQL对象
			//静态SQL对象：直接定义，直接通过字符串拼接的方式将参数放到SQL中
			//SQL注入
//			statement = connection.createStatement();
//			String sql = "select * from user where username = '" + username + "'";
			
			//2.2 创建PreparedStatement执行动态SQL对象
			//?：通配符
			String sql = "select * from user where username = ?";
			//PrepareStatement：预编译SQL语句
			PreparedStatement pStatement = connection.prepareStatement(sql);
			//setXxx(?位置,?对应的参数)
			pStatement.setString(1, username);
			//2.3 执行SQL语句
			result = pStatement.executeQuery();
			
			
			
			
			
			
			
			
			if (result.next()) {//有数据
				String passwordResult = result.getString("password");
				
				
				//2.4 判断密码是否正确
				if(password.equals(passwordResult)){
					System.out.println("登陆成功！！！");
				}else{
					System.out.println("登陆失败，用户名或密码错误~~~");
				}
			}else{//无数据
				System.out.println("登陆失败，用户名或密码错误~~~");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			//3. 关闭资源
			JDBCUtils.close(result, statement, connection);
		}
	}
	
	
//1. 控制台输入用户名、密码
//2. 获取数据库数据，判断用户名和密码是否正确（JDBC）
// String sql = "select * from user where username = '" + username + "'";
//3. 关闭资源
	
	
//2.
//	###JDBC流程
//	1. 加载注册驱动
//	2. 获取数据库连接对象（Connection）
//	3. 获取执行SQL对象（Statement）
//	4. 获取结果集对象（ResultSet）
//	5. 遍历获取数据
//	6. 关闭资源
	
	
	
	
	
	
	
	
	
	
	
	
}

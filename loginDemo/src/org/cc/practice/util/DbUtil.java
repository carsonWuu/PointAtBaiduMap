package org.cc.practice.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class DbUtil {

	private static Properties db;
	
	static{
		try {
			db=new Properties();
			db.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			Class.forName(db.getProperty("driver"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		
		Connection conn=null;
		
		try {
//			conn=DriverManager.getConnection("jdbc:mysql:///test_001","root","toor");
			conn=DriverManager.getConnection(db.getProperty("url"),db);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn){
		try {
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt){
		try {
			if(pstmt!=null && !pstmt.isClosed()){
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try {
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

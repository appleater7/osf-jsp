package com.osf.test.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon {
	private static final String URL; // 선언
	private static final String USER;
	private static final String PASSWORD;
	private static final String DRIVER;
	
	public static final String CLIENT_ID;
	public static final String CLIENT_SECRET;
	public static final String NAVER_URL;
	public static final String METHOD;
	
	static {
		InputStream is = DBCon.class.getResourceAsStream("/com/osf/test/config/db.properties");
		// 풀경로적어도 동작하나... 자바절대경로는 src 부터 작동한다.
		Properties prop = new Properties();
		try {
			prop.load(is);				
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = prop.getProperty("url");
		USER = prop.getProperty("user");
		PASSWORD = prop.getProperty("password");
		DRIVER = prop.getProperty("classname");
		
		CLIENT_ID = prop.getProperty("clientId");
		CLIENT_SECRET= prop.getProperty("clientSecret");
		NAVER_URL= prop.getProperty("apiURL");
		METHOD = prop.getProperty("Method");
	}
	private static Connection con = null;
	private static void open() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getCon() {
		if (con == null) {
			open();
		}
		return con;		
	}
	public static void close() {
		if (con != null) {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		con = null;
	}
	public static void main(String[] args) {
		getCon();
		close();
	}
}

package com.sunbeaminfo.utils;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	public static Properties prop;
	
	static {
		prop = new Properties();
		
		try (Reader in =new FileReader("jdbc.properties")){
			System.out.println(in);
			prop.load(in);
			Class.forName(prop.getProperty("DRIVER"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("USER"),prop.getProperty("PASSWORD"));
	}
}

package com.yichao.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author Administrator
 *
 */
public class DbHelper {

	// --下节课 使用Properties配置文件.将四个常量移到配置文件中.
	

	private Connection mConnection;

	/**
	 * 返回和数据库的连接对象.
	 * 
	 * @return
	 */
	public Connection getConnection() {
		if (mConnection == null) {
			
			try {
				Properties p = new Properties();
				File f = new File("config/config.properties");
				InputStream is = new FileInputStream(f);
				p.load(is);
				String driver =p.getProperty("DRIVER");
				String url =p.getProperty("URL");
				String user =p.getProperty("USER");
				String pwd =p.getProperty("PWD");
				// --为了纪念记忆.是可以不写的.目的是加载驱动.
				Class.forName(driver);
				mConnection = DriverManager.getConnection(url, user, pwd);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {				
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}

		return mConnection;

	}

}

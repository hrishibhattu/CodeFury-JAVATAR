/*
 * Connect Application with MySQL database
 * */

package com.toyodo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
 * @author JAVATAR
 * */

public class DBConnection {
	private static Connection con;
	private static ResourceBundle resource;

	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//	read a properties file with a Resource Bundle
			resource = ResourceBundle.getBundle("com.toyodo.utils.dbresource");
			//	establishes a database connection
			con = DriverManager.getConnection(resource.getString("db.url"), resource.getString("db.id"), resource.getString("db.password"));
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println(ex);
		}
		return con;
	}

	public static void main(String[] args) {
		Connection con = createConnection();
		System.out.println(con);
	}
}

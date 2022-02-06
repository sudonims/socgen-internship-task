package A3.A;

import java.sql.*;

class DBConnection {
	private static Connection conn = null;

	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Driver loaded");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/test", "", "");
			System.out.println("Driver Connected");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		return conn;
	}
}
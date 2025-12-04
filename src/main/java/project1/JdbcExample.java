package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcExample {
	public static Connection getConnection() {
		Connection con= null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/raja","root","Rk18++++");
		}catch(Exception d) {
			System.out.println(d);
		}
		return con;
	}

}

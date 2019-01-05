package Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "admin123");
		System.out.println(con);
		
		
		//DDL, DML, DRL
		
		con.close();
	}

}

package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JdbcTest {

	public static void main(String[] args) throws Exception{
		//Step 1 : Load driver
		
		//Type-1
		//Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Type-4
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//Step 2 : Get connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Deepak_DB", "root", "admin123");
		System.out.println(con);
		
		//Step 3 and 4: Create and execute statements - DDL, DML, DRL
		
		Statement cst = con.createStatement();
		
		//String ddlDropStr = "drop table students";
		//cst.execute(ddlDropStr);
		
		//String dmlInsertStr = "create table students(id int, name varchar(20), email varchar(50));";
		//cst.execute(dmlInsertStr);
		
		//String dmlInsertStr = "insert into students(id,name,email) values(3, 'name3', 'name3@gmail.com')";
		//int noRowsAffected = cst.executeUpdate(dmlInsertStr);
		
		String drlSelectStr = "select * from students;";
		ResultSet rset = cst.executeQuery(drlSelectStr);
		
		//Step 5 : Access data from ResultSet
		ResultSetMetaData metadata = rset.getMetaData();
		
//		while(rset.next())
//		{
//			System.out.println("Id: " + rset.getInt(1));
//			System.out.println("Name: " + rset.getString(2));
//			System.out.println("Email: " + rset.getString(3));
//		}
		
		for(int i = 1; i <= metadata.getColumnCount(); i++)
		{
			System.out.println("Col no "+ i);
			System.out.println("Col name "+ metadata.getColumnName(i));
			System.out.println("Col data type "+ metadata.getColumnTypeName(i));
		}
		
		while(rset.next())
		{
			System.out.println("Id: " + rset.getInt("id"));
			System.out.println("Name: " + rset.getString("name"));
			System.out.println("Email: " + rset.getString("email"));
		}
		
		//System.out.println(noRowsAffected + " rows added into students table..!!");
		
		//close connection
		con.close();
	}

}

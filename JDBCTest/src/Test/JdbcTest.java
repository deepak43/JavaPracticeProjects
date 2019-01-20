package Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class JdbcTest {

	public static void main(String[] args) throws Exception{
		
		/*  Step 0 : Load properties file. Its used for getting connection in Method 2 */
		 
		Properties prop = new Properties();
		prop.load(new FileInputStream("../JDBCTest/resources/DB.properties"));
		
		
		/* Step 1 : Load driver  */
		
		//Method 1
		Class.forName(prop.getProperty("driver")); // Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Method 2
		//Driver driver = new Driver();
		//DriverManager.registerDriver(driver);
		
		
		
		
		/* Step 2 : Get connection */
		
		//Method 2
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Deepak_DB", "root", "admin123");
		
		//Method 3
		Connection con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
		//System.out.println(con);
		
		
		
		
		/* Step 3 and 4: Create and execute statements - DDL, DML, DRL  */
		
		Statement cst = con.createStatement();
		
		//String ddlDropStr = "drop table students";
		//cst.execute(ddlDropStr);
		
		//String dmlInsertStr = "create table students(id int, name varchar(20), email varchar(50));";
		//cst.execute(dmlInsertStr);
		
		//String dmlInsertStr = "insert into students(id,name,email) values(3, 'name3', 'name3@gmail.com')";
		//int noRowsAffected = cst.executeUpdate(dmlInsertStr);

		
		PreparedStatement pst = con.prepareStatement("insert into students(id,name,email) values(?, ?, ?)");
//		pst.setInt(1, 4);
//		pst.setString(2, "name4");
//		pst.setString(3, "name4@gmail.com");
//		int noRowsAffected = pst.executeUpdate();
		
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("Enter Id: ");
			pst.setInt(1, sc.nextInt());
			
			System.out.println("Enter Name: ");
			pst.setString(2, sc.next());
			
			System.out.println("Enter Email: ");
			pst.setString(3, sc.next());
			
			pst.executeUpdate();
			
			System.out.println("1 record added...!");
			System.out.println("To add more records press 1.");
			System.out.println("To see all records press 2.");
			System.out.println("To exit press 0.");
			int choice = sc.nextInt();
			
			if(choice == 0)
			{
				sc.close();
				System.exit(0);
			}
			else if(choice == 2)
				break;
		}
		sc.close();
		
		
		/* Step 5 : Access data from ResultSet */
		
		String drlSelectStr = "select * from students;";
		ResultSet rset = cst.executeQuery(drlSelectStr);
		
		//ResultSetMetaData metadata = rset.getMetaData();
		
		while(rset.next())
		{
			System.out.println("Id: " + rset.getInt(1));
			System.out.println("Name: " + rset.getString(2));
			System.out.println("Email: " + rset.getString(3));
		}
		
//		for(int i = 1; i <= metadata.getColumnCount(); i++)
//		{
//			System.out.println("Col no "+ i);
//			System.out.println("Col name "+ metadata.getColumnName(i));
//			System.out.println("Col data type "+ metadata.getColumnTypeName(i));
//		}
//		
//		while(rset.next())
//		{
//			System.out.println("Id: " + rset.getInt("id"));
//			System.out.println("Name: " + rset.getString("name"));
//			System.out.println("Email: " + rset.getString("email"));
//		}
		
		//System.out.println(noRowsAffected + " rows added into students table..!!");
		
		
		
		
		//Step 7 : close connection
		con.close();
	}

}

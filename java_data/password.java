package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class password  {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try{	
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung";
	           String id="medisook";
	           String password="1715231";
			   Connection con = DriverManager.getConnection(connectionUrl,id,password);
	           Statement stmt = con.createStatement();
	           System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
	          
		       con.close(); 
		       
		    }catch(SQLException e) {
		    	System.out.println(e+ "=> Sql 예외 ");
		    }catch (Exception e) {
				e.printStackTrace();
		    }

	}



}

package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.*;



public class makewarning {//성분코드랑 성분이랑 분리
	public void jsonex() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung;integratedSecurity=true";
			   Connection con = DriverManager.getConnection(connectionUrl);
	           Statement stmt1 = con.createStatement();
	           Statement stmt2 = con.createStatement();
	           ResultSet rs=null;
	           ResultSet rs2=null;
	           PreparedStatement pstmt = null;
	           List<String> ingrlist = new ArrayList<String>();
	           System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
	          //여기 수정해야함 
	           String sql = "UPDATE TOTALINFO SET WARNING=? WHERE DRUG_NAME=?";
	          
	     
	           
	        	   String sql1="SELECT DRUG_NAME,WARNING FROM TOTALINFO WHERE QESITM LIKE '%음주%'";
	        	   
	        	   rs = stmt1.executeQuery(sql1);
	        	   //System.out.println(rs.toString());
	        	   
	        	   
	        	   while(rs.next()) {
	        		  
	        		   String name="";
	        		   String warning="";
	        		   	        		        	
	        		   name=rs.getString("DRUG_NAME");
	        		   warning=rs.getString("WARNING");
	        		   pstmt = con.prepareStatement(sql);        	
	        		   
	        		   if(warning!=null) {
	        			   pstmt.setString(1,warning+",음주");
		        		   pstmt.setString(2,name);
	        		   }
	        		   
	        		   else {
	        			   pstmt.setString(1,"음주");
		        		   pstmt.setString(2,name);
	        		   }
	        		   
//	        		   	    
//	        		   
	        		   int resultsql = pstmt.executeUpdate();
	        		   
	        		   
	        		   
					       
	        	   }
	        	   
	        	   stmt1.close();  
	        	   
	        	   
	        
	        	   
				    				
				    				

						 
						    					

					    	        

	        	   	          
		    	
		        con.close();
		        
		
		}catch(SQLException e) {
			System.out.println(e+ "=> Sql 예외 ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
				
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		makewarning tt=new makewarning();
		tt.jsonex();
		
	}

}

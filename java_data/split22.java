package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;



public class split22 {//성분코드랑 성분이랑 분리
	public void jsonex() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung;integratedSecurity=true";
			   Connection con = DriverManager.getConnection(connectionUrl);
	           Statement stmt = con.createStatement();
	           ResultSet rs=null;
	           PreparedStatement pstmt = null;
	           System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
	           String sql = "INSERT INTO SPLIT (INGR_CODE,INGR_NAME)"
				+ " VALUES (?,?)";//여기 수정해야함 
	     
	           
	        	   String sql1="SELECT DISTINCT MAIN_INGR FROM NEWDRUGINFO";
	        	   rs = stmt.executeQuery(sql1);
	        	   //System.out.println(rs.toString());
	        	   
	        	   
	        	   while(rs.next()) {
	        		  
	        		   String ingr="";
	        		   
	        		   String tmp1="";
	        		   String tmp2="";
	        		   String tmp3="";
	        		   String tmp4="";
	        		       	
	        		   ingr=rs.getString("MAIN_INGR");
	        		   
	        		   if(ingr==null) 
	        			   continue;
	        		 
	        		   
	        		   String[] splitn= ingr.split("\\[|\\]|\\|");
	        		   //String[] array= {};
			           for(int i=0;i<splitn.length;i++) 
					  {  		
						  
						  if(splitn[i].isEmpty()) {
							  continue;
						  }
						  else if(splitn[i].charAt(0)=='A') {
							  continue;
						  }
						  else if(splitn[i].charAt(0)=='B') {
							  continue;
						  }
						  else if(splitn[i].isEmpty()) {
							  continue;
						  }
						  else if(splitn[i].charAt(0)=='M') {
							  if(splitn[i].charAt(1)=='0'||splitn[i].charAt(1)=='1'||splitn[i].charAt(1)=='2') {
								  System.out.println(splitn[i]);
								  pstmt = con.prepareStatement(sql);
								  pstmt.setString(1, splitn[i]);
								  pstmt.setString(2, splitn[i+1]);
								  
							  }
							  				 						  
						  }
						 
					  } 
					
		
				    	int resultsql = pstmt.executeUpdate();
				    					
	        	   }
	        	   	          
		    	stmt.close();   
		        con.close();
		
		}catch(SQLException e) {
			System.out.println(e+ "=> Sql 예외 ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
				
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		split22 tt=new split22();
		tt.jsonex();
		
	}

}

package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.*;



public class imbucount {//�����ڵ�� �����̶� �и�
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
	           System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
	          //���� �����ؾ��� 
	           String sql = "UPDATE TOTALINFO SET IMBU_COUNT=? WHERE DRUG_NAME=?";
	          
	     
	           
	        	   String sql1="SELECT DRUG_NAME,IMBU_COUNT,MAIN_INGR FROM TOTALINFO WHERE IMBU_COUNT IS NOT NULL";
	        	   
	        	   rs = stmt1.executeQuery(sql1);
	        	   //System.out.println(rs.toString());
	        	   
	        	   
	        	   while(rs.next()) {
	        		  
	        		   String name="";
	        		   String ingr="";
	        		   int count;
	        		   //int tmp;
	        		   
	        		  
	        		        	
	        		   name=rs.getString("DRUG_NAME");
	        		   ingr=rs.getString("MAIN_INGR");
	        		   count=rs.getInt("IMBU_COUNT");
	        		   
	        		   String[] spl=ingr.split("\\|");
	        		   
	        		   if(count>spl.length) {
	        			   count=spl.length;
	        		   }
	        		  
	        		  
	        		   
	        		  
	        		   pstmt = con.prepareStatement(sql);        		    
	        		   pstmt.setInt(1,count);
	        		   pstmt.setString(2,name);
	        		   int resultsql = pstmt.executeUpdate();
	        		   
	        		   
	        		   
					       
	        	   }
	        	   
	        	   stmt1.close();  
	        	   pstmt.close();
	        	   
	        	   
	        
	        	   
				    				
				    				

						 
						    					

					    	        

	        	   	          
		    	
		        con.close();
		        
		
		}catch(SQLException e) {
			System.out.println(e+ "=> Sql ���� ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
				
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		imbucount tt=new imbucount();
		tt.jsonex();
		
	}

}

package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;



public class makecom {//�����ڵ�� �����̶� �и�
	public void jsonex() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung;integratedSecurity=true";
			   Connection con = DriverManager.getConnection(connectionUrl);
	           Statement stmt = con.createStatement();
	           ResultSet rs=null;
	           PreparedStatement pstmt = null;
	           System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
	        
	           
	        	   String sql1="SELECT DISTINCT INGR_NAME FROM SPLIT";
	        	   rs = stmt.executeQuery(sql1);
	        	   //System.out.println(rs.toString());
	        	   
	        	   
	        	   while(rs.next()) {
	        		  
	        		   String ingr="";
	        		   
	        		   String tmp1="";
	        		   String tmp2="";
	        		   	        		   	        	
	        		   ingr=rs.getString("INGR_NAME");
	        		
	        		   
	        		   tmp1='"'+ingr+'"'+',';
	        		   System.out.println(tmp1);
	        		   
	        		   
	        	
						 
	        		   
	 
	        		   
	        		   
	        		   
	        	
	        		
				    					//pstmt = con.prepareStatement(sql);
				    					//JSONObject drug = (JSONObject) parse_item.get("item");
				    					//System.out.println(drug.toString());
				    					//String mix_t = (String) drug.get("itmNm");// ��ǰ�̸�
				    					//pstmt.setString(1, mix_t);
				    				    ///String ingr_code = (String) drug.get("gnlNmCd");//�����ڵ� 
				    					//pstmt.setString(2,ingr_code); 
				    					//String ingr_Ename = String.valueOf(drug.get("mdsCd")) ;//��ǰ�ڵ�
				    					//pstmt.setString(3, ingr_Ename); 
				    					

				    					
	        	   }
				    				
				    				

						 //  int resultsql = pstmt.executeUpdate();
						    					

					    	        

	        	   	          
		    	stmt.close();   
		        con.close();
		
		}catch(SQLException e) {
			System.out.println(e+ "=> Sql ���� ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
				
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		makecom tt=new makecom();
		tt.jsonex();
		
	}

}

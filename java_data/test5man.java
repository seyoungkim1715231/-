package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class test5man {
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
	           String sql = "INSERT INTO TEST1 (DURG_NAME, EFCY, USEMETHOD, QESITM)"
	           + " VALUES (?,?,?,?)";//여기 수정해야함 
	     
	           
	        	   String sql1="SELECT * FROM NEWDRUGINFO";
	        	   rs = stmt.executeQuery(sql1);
	        	   //System.out.println(rs.toString());
	        	   
	        	   String name="";	        
	        	   
	        	   
	        	   
	        	   while(rs.next()) {
	        		   
	        		   String efcy="";
	        		   String usemethod="";
	        		   String qesitm="";
	        		   
	        		   String tmp="";
	        		   
	        		   name=rs.getString("DRUG_NAME");
	        		   efcy=rs.getString("EFCY");
	        		   usemethod=rs.getString("USEMETHOD");
	        		   qesitm=rs.getString("QESITM");
	        		   pstmt = con.prepareStatement(sql);
	        		   
	        		  
	        	       String [] arr= {efcy,usemethod,qesitm};
	        	       
	        	       pstmt.setString(1, name);
	        	       
	        	       for(int i=0;i<3;i++) {
	        	    	   
	        	    	   String content="";
	        	    	  
	        	    	   if (arr[i]==null) {
		        			   
		        			   pstmt.setString(i+2,arr[i]);
		        			   
		        			  
		        		   }
		        		   
		        		   else if(arr[i]!=null) {
		        			   
		        			   if(arr[i].contains("&nbsp;")) {
			        			   tmp=arr[i].replace("&nbsp;"," ");
			        			   arr[i]=tmp;
			        		   }
		        			   
		        			   if(arr[i].contains("<!--StartFragment-->")) {
		        				   tmp=arr[i].replace("<!--StartFragment-->"," ");
		        				   arr[i]=tmp;
		        			   }
		        			   
		        			   if(arr[i].contains("&#x2024;")) {
		        				   tmp=arr[i].replace("&#x2024;"," ");
		        				   arr[i]=tmp;
		        			   }
		        			   
		        			   if(arr[i].contains("&#x3007;")) {
		        				   tmp=arr[i].replace("&#x3007;"," ");
		        				   arr[i]=tmp;
		        			   }
		        			   
		        			 
		        			    if(arr[i].contains("<sub>")) {
		        				   tmp=arr[i].replace("<sub>"," ");
		        				   arr[i]=tmp;
		        			   }
		        			    
		        			   if(arr[i].contains("</sub>")) {
		        				   tmp=arr[i].replace("</sub>"," ");
		        				   arr[i]=tmp;
		        			   }
		        			   
		        			          			   
		        			   if(arr[i].contains("<sup>")) {
		        				   tmp=arr[i].replace("<sup>"," ");
		        				  
		        				   arr[i]=tmp;
		        			   }
		        			   
		        			   if(arr[i].contains("</sup>")) {
		        				   tmp=arr[i].replace("</sup>"," ");
		        				   arr[i]=tmp;
		        			   }
		        			   
		        			   if(arr[i].contains("&#x8226;")) {
		        				   tmp=arr[i].replace("&#x8226;"," ");
		        				   arr[i]=tmp;
		        			   }
		        			   
		        			   if(arr[i].contains("&#x2981;")) {
		        				   tmp=arr[i].replace("&#x2981;"," ");
		        				   arr[i]=tmp;
		        			   }
		        			 
		        			   
		        			   
		        			   Document doc =Jsoup.parse(arr[i]);
		        			 
		  	        		 
			        		   Elements links2 = doc.getElementsByTag("SECTION");
			        		   
			        		   for (Element link : links2) {
			                       content=content+link.text();
			                   }
			        		   //content find로 '&nbsp'가 있으면 reaplace 공백으로 바꾼다.
			        		   //그리고 나서 담는다. 
			        		   //System.out.println(content);
			        		  
			        		   pstmt.setString(i+2, content);
		        			   
		        		   }
	        	       }
	        		   
	        		  	  
	        		
	        		   int resultsql = pstmt.executeUpdate();
	        	
				}
				    				
				    				

						 //  int resultsql = pstmt.executeUpdate();
						    					

					    	        

	        	   	          
		    	stmt.close();   
		        con.close();
		
		}catch(SQLException e) {
			System.out.println(e+ "=> Sql 예외 ");
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
				
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		test5man tt=new test5man();
		tt.jsonex();
		
	}

}

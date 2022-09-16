package dbtest;
//�����ͺ��̽����� ���̸� �ҷ����� 
//for�������� �����鼭 url������
import java.sql.*;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class noin {
public void datainfo()throws ClassNotFoundException {//Ư���������� ����
		
		try{	
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung;integratedSecurity=true";
			   Connection con = DriverManager.getConnection(connectionUrl);
	           Statement stmt = con.createStatement();
	           System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
	           String sql1="SELECT NUM,DRUG_NAME FROM EFCYINFO";
	           String sql = "INSERT INTO NOINWARNING (INGR_CODE, INGR_NAME, PROHBT_CONTENT) VALUES (?,?,?)";
	           PreparedStatement pstmt = null;
	           int num=0;
	           int number=0;//�� number�� efcyinfo���̺��� NUM��.
	           
	           for (int i=1;i<=number;number++) {
	        	   
	        	   
	           }

	          
		    	for (int page=1;page<3;page++) {//4����
		    		 String pageNum=Integer.toString(page);
		    		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DURIrdntInfoService01/getOdsnAtentInfoList"); /*URL*/
		    	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=LgPg%2BnD2Oy1X1zQBRYI%2FoGjOVdSa17g9KbfkO2xI7ZF68WYcYHtkCncTTmzS5Uw22mSynmpVfnW7TlF0o5WexA%3D%3D"); /*Service Key*/
		    	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNum, "UTF-8")); /*��������ȣ*/
		    	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*�� ������ ��� ��*/
		    	        
		    	        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*���䵥���� ����(xml/json) default : xml*/
		    	        URL url = new URL(urlBuilder.toString());
		    	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    	        conn.setRequestMethod("GET");
		    	        conn.setRequestProperty("Content-type", "application/json");
		    	        System.out.println("Response code: " + conn.getResponseCode());
		    	       
		    	        BufferedReader rd;
		    	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		    	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		    	        } else {
		    	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		    	        }
		    	        String line;
		    	        String result="";		    	    
		    	        while ((line = rd.readLine()) != null) {
		    	        	result = result.concat(line);
		    	        }
		    	        
		    	        JSONParser parser = new JSONParser();	
		    			JSONObject obj=(JSONObject)parser.parse(result);
		    			
		    			
		    			JSONObject parse_body=(JSONObject)obj.get("body");
		    			//System.out.println(parse_body.toString());
		    			JSONArray parse_listArr = (JSONArray) parse_body.get("items");
		    			/* System.out.println(parse_listArr.toString()); *///����Ʈ�� ������ ���� 
		    			//System.out.println(parse_listArr.size());//3���� ���������� ����
		    			
		    			if (parse_listArr.size() > 0) {
		    				//pstmt = con.prepareStatement(sql);
		    				for (int i = 0; i < parse_listArr.size(); i++) {
		    					pstmt = con.prepareStatement(sql);
		    					
		    					JSONObject drug = (JSONObject) parse_listArr.get(i);
		    			
		    					String ingr_code = (String) drug.get("INGR_CODE");//�����ڵ�
		    					pstmt.setString(1, ingr_code);
		    					
		    					String ingr_Kname = (String) drug.get("INGR_NAME");//�����ѱ�
		    					pstmt.setString(2, ingr_Kname);
		    				
		    					String warncontent = (String) drug.get("PROHBT_CONTENT");//�ݱ⳻��
		    					pstmt.setString(3, warncontent);

		    					
		    					
		    					
		 
		    					int resultsql = pstmt.executeUpdate();
				    			System.out.println("ó���� ���ڵ� ��"+resultsql);
				    			
		    				}

		    			}
		    			
		    	        rd.close();
		    	        conn.disconnect();
		    			/* System.out.println(sb.toString()); */
		    		
		    	}
		    	stmt.close();   
		        con.close(); 
		        pstmt.close();
		    }catch(SQLException e) {
		    	System.out.println(e+ "=> Sql ���� ");
		    }catch (Exception e) {
				e.printStackTrace();
		    }

	}
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		noin ct = new noin();
		ct.datainfo();
  
		
		
       
    }//rs.close( );stmt.close( );conn.close( ); ����ó���� �� ���� �ݾ��ֱ�

}



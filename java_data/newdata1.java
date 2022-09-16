package dbtest;

import java.sql.*;
import java.sql.SQLException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class newdata1 {

	public void datainfo()throws ClassNotFoundException {//병용금기
		
		try{	
			
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	           String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung;integratedSecurity=true";
			   Connection con = DriverManager.getConnection(connectionUrl);
	           Statement stmt = con.createStatement();
	           System.out.println("MS-SQL 서버 접속에 성공하였습니다.");
	           //String sql = "INSERT INTO NEWDRUGINFO (DRUG_NAME,DRUG_CODE, ENTP_NAME, OTC, CHART, EFCY, "
	           //+"USEMETHOD, QESITM, DEPOSIT, VALID_TERM, TOTAL_CONTENT,MAIN_INGR, INGR_NAME)"
	           //+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	           PreparedStatement pstmt = null;
	           
	           	          
		    	for (int page=1;page<524;page++) {//4까지
		    		 String pageNum=Integer.toString(page);
		    		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrugPrdtPrmsnInfoService02/getDrugPrdtPrmsnDtlInq01"); /*URL*/
		    	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=LgPg%2BnD2Oy1X1zQBRYI%2FoGjOVdSa17g9KbfkO2xI7ZF68WYcYHtkCncTTmzS5Uw22mSynmpVfnW7TlF0o5WexA%3D%3D"); /*Service Key*/
		    	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNum, "UTF-8")); /*페이지번호*/
		    	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
		    	        
		    	        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) default : xml*/
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
		    			
		    			if (parse_listArr.size() > 0) {
		    				
		    				for (int i = 0; i < parse_listArr.size(); i++) {
		    					pstmt = con.prepareStatement(sql);
		    
		    					JSONObject drug = (JSONObject) parse_listArr.get(i);
		    					/* System.out.println(drug.toString()); */
		    					
		    					String mix_t = (String) drug.get("ITEM_NAME");//약이름
		    					pstmt.setString(1, mix_t);
		    					//System.out.println(mix_t);
		    					String ingr_code = (String) drug.get("ITEM_SEQ");//약코드
		    					pstmt.setString(2, ingr_code);
		    					String ingr_Ename = (String) drug.get("ENTP_NAME");//회사명
		    					pstmt.setString(3, ingr_Ename);
		    					String ingr_Kname = (String) drug.get("ETC_OTC_CODE");//전문일반
		    					pstmt.setString(4, ingr_Kname);
		    					String ori = (String) drug.get("CHART");//약효
		    					pstmt.setString(5, ori);
		    					String efclass = (String) drug.get("EE_DOC_DATA");//용법
		    					pstmt.setString(6, efclass);
		    					String mixt_t = (String) drug.get("UD_DOC_DATA");//qesitm
		    					pstmt.setString(7, mixt_t);
		    					String mIngr_code = (String) drug.get("NB_DOC_DATA");//보관
		    					pstmt.setString(8, mIngr_code);
		    					String mIngr_Ename = (String) drug.get("STORAGE_METHOD");//유효기간
		    					pstmt.setString(9, mIngr_Ename);
		    					String valid_term = (String) drug.get("VALID_TERM");//첨가제품
		    					pstmt.setString(10, valid_term);
		    					String mIngr_Kname = (String) drug.get("MATERIAL_NAME");//총량
		    					pstmt.setString(11, mIngr_Kname);
		    					String m_ori = (String) drug.get("MAIN_ITEM_INGR");//주성분
		    					pstmt.setString(12, m_ori);
		    					String mefclass = (String) drug.get("INGR_NAME");//첨가제품
		    					pstmt.setString(13, mefclass);
		    				

		    					
		    					
		    					//int resultsql = pstmt.executeUpdate();
				    			//System.out.println("처리된 레코드 수"+resultsql);
				    			
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
		    	System.out.println(e+ "=> Sql 예외 ");
		    }catch (Exception e) {
				e.printStackTrace();
		    }

	}
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		newdata1 ct = new newdata1();
		ct.datainfo();
  
		
		
       
    }//rs.close( );stmt.close( );conn.close( ); 예외처리로 다 쓰면 닫아주기

}

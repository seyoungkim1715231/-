package dbtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import org.json.simple.JSONObject;


public class xmltojson {
	public void datainfo() throws ClassNotFoundException {

		try {

			System.out.println("MS-SQL ���� ���ӿ� �����Ͽ����ϴ�.");
			String sql = "INSERT INTO TERMWARNING (NUM,MIX_TYPE, INGR_CODE, INGR_ENG_NAME, INGR_NAME,MIX_INGR, ORI_INGR, CLASS_NAME, FORM_NAME, MAX_TERM, PROHBT_CONTENT)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = null;
			
			
			stmt.close();
			con.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e + "=> Sql ���� ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException {
		
		test tt=new test();
		tt.jsonex();
		
		

	}// rs.close( );stmt.close( );conn.close( ); ����ó���� �� ���� �ݾ��ֱ�
}

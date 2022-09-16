package dbtest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class symptom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		try {

			FileInputStream input = new FileInputStream("C:\\Users\\82103\\Desktop\\성분1.txt");
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			// File text = new File("C:\\Users\\82103\\Desktop\\효능.txt");
			// FileReader textRead = new FileReader(text);
			BufferedReader bfReader = new BufferedReader(reader);
			String line = "";
			List<String> lineArray = new ArrayList<String>();// null 이 아닐때까지 반복한다.
			while ((line = bfReader.readLine()) != null) {
				// System.out.println(line); 출력// 리스트 배열에 line 한줄한줄씩 추가.
				lineArray.add(line);
			}
			for (int i = 0; i < lineArray.size(); i++) {
				/*
				 * String sym=""; String tmp="";
				 * 
				 * String tmp=""; sym=lineArray.get(i); for(int j=0;j<sym.length();j++) {
				 * if(sym.charAt(0)==' ') { continue; } else { tmp=tmp+sym.charAt(j); }
				 * 
				 * }
				 * 
				 * //sym=tmp; sym=lineArray.get(i).replaceAll(" ",""); tmp='"'+sym+'"'+',';
				 */
				System.out.print(lineArray.get(i));
			}

			input.close();

		} catch (FileNotFoundException e) {// 파일 없거나 이름 안맞으면 여기걸림.
			e.printStackTrace();
			System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

package dbtest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class symptom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		try {

			FileInputStream input = new FileInputStream("C:\\Users\\82103\\Desktop\\����1.txt");
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			// File text = new File("C:\\Users\\82103\\Desktop\\ȿ��.txt");
			// FileReader textRead = new FileReader(text);
			BufferedReader bfReader = new BufferedReader(reader);
			String line = "";
			List<String> lineArray = new ArrayList<String>();// null �� �ƴҶ����� �ݺ��Ѵ�.
			while ((line = bfReader.readLine()) != null) {
				// System.out.println(line); ���// ����Ʈ �迭�� line �������پ� �߰�.
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

		} catch (FileNotFoundException e) {// ���� ���ų� �̸� �ȸ����� ����ɸ�.
			e.printStackTrace();
			System.out.println("������ ���������ʽ��ϴ�. ��θ� Ȯ�����ּ���");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

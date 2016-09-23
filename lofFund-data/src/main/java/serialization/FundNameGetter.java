package serialization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FundNameGetter {

	private final String filePath = "D:\\Competiton\\花旗杯\\lofdata\\data\\Code&Name.txt";
	/**
	 * 
	 * @return 获得所有基金的代码和名字
	 */
	public ArrayList<String> getFundNameAndCode(){
		File f = new File(filePath);
		BufferedReader codeName = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			codeName = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));  
			String text = "";
			while ((text = codeName.readLine()) != null) {
				list.add(text);
			}
			codeName.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
	}
}

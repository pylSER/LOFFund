package serialization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import dataService.LatestDataGetterService;

public class LatestDataGetter implements LatestDataGetterService {

	private String filePath = "D:\\Competiton\\花旗杯\\lofdata\\data\\rt_data.txt";

	public double[] getLatestData(String codeNumber) {
		File currtimefile = new File(filePath);
		double[] reslist = new double[3];

		try {
			BufferedReader index = new BufferedReader(new FileReader(currtimefile));
			String text = null;
			String[] sp = null;
			while ((text = index.readLine()) != null) {
				sp = text.split(" ");
				if (codeNumber.contains(sp[0])) {
					for (int i = 0; i < reslist.length; i++) {
						reslist[i] = Double.parseDouble(sp[i + 1]);
					}
					break;
				}
			}
			index.close();

			return reslist;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new double[3];
	}
}

package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RealValueGetter {

	String filepathtoday = "D:\\Competiton\\花旗杯\\lofdata\\data\\";
	String currentPath="D:\\Competiton\\花旗杯\\lofdata\\data\\currtime.txt";

	/**
	 * 
	 * @param code
	 * @param days
	 * @return 获得days天的净值，升序，最近的在最后
	 */
	public double[] getRecentValue(String code, int days) { // 获得最近days天的LeftR
		// 第一个是最近一天的数据
		File currtimefile = new File(currentPath);
		double[] reslist = new double[days];

		try {
			BufferedReader bryesterday = new BufferedReader(new FileReader(currtimefile));
			String text = "";
			text = bryesterday.readLine();

			for (int i = 0; i < days; i++) {
				double p = getRealByDate(code, text);
				text = getSpecifiedDayBefore(text);
				
				reslist[days - i - 1] = p;
			}

			bryesterday.close();

			return reslist;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new double[0];
	}

	// 获得该基金某一天的净值 日期格式 2016-09-09
	public double getRealByDate(String code, String date) {
		double todayP = 0;

		String filetodayname = filepathtoday + date + ".txt";
		File filetoday = new File(filetodayname);
		BufferedReader brtoday = null;
		try {
			brtoday = new BufferedReader(new FileReader(filetoday));

			String text = "";

			while ((text = brtoday.readLine()) != null) {
				String txtcode = text.split(" ")[1];
				if (txtcode.equals(code)) {
					todayP = Double.parseDouble(text.split(" ")[0]);
					break;
				}
			}
			brtoday.close();
			return todayP;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return todayP;
	}

	private String getSpecifiedDayBefore(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
}

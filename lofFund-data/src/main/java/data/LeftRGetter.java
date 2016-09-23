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

import dataService.LeftRGetterService;

public class LeftRGetter implements LeftRGetterService {

	String filepathtoday = "D:\\Competiton\\花旗杯\\lofdata\\data\\";
	String filepathyesterday = "D:\\Competiton\\花旗杯\\lofdata\\data\\";

	public double[] getRecentLeftR(String code, int days) { // 获得最近days天的LeftR
															// 第一个是最近一天的数据
		File currtimefile = new File("D:\\Competiton\\花旗杯\\lofdata\\data\\currtime.txt");
		double[] reslist = new double[days];

		try {
			BufferedReader bryesterday = new BufferedReader(new FileReader(currtimefile));
			String text = "";
			text = bryesterday.readLine();

			for (int i = 0; i < days; i++) {
				double r = getLeftRByDate(code, text);
				text = getSpecifiedDayBefore(text);

				reslist[i] = r;
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

	// 获得该基金某一天的R 日期格式 2016－09-09
	public double getLeftRByDate(String code, String date) {
		double yesterdayP = 0;
		double todayP = 0;

		String filetodayname = filepathtoday + date + ".txt";
		String fileyesterdayname = filepathtoday;

		String yesterdaydate = getSpecifiedDayBefore(date);

		fileyesterdayname += yesterdaydate;
		fileyesterdayname += ".txt";

		File fileyesterday = new File(fileyesterdayname);
		File filetoday = new File(filetodayname);

		try {
			BufferedReader bryesterday = new BufferedReader(new FileReader(fileyesterday));
			BufferedReader brtoday = new BufferedReader(new FileReader(filetoday));

			String text = "";

			while ((text = bryesterday.readLine()) != null) {
				String txtcode = text.split(" ")[1];
				if (txtcode.equals(code)) {
					yesterdayP = Double.parseDouble(text.split(" ")[0]);
					break;
				}
			}

			while ((text = brtoday.readLine()) != null) {
				String txtcode = text.split(" ")[1];
				if (txtcode.equals(code)) {
					todayP = Double.parseDouble(text.split(" ")[0]);
					break;
				}
			}

			brtoday.close();
			bryesterday.close();

			if (yesterdayP == 0) {
				return 1;
			}

			double a = Math.log(todayP / yesterdayP);

			return a;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;

	}

	// 获得该基金最近的一个R值
	public double getLeftR(String code) {

		double yesterdayP = 0;
		double todayP = 0;

		calculateFileName();
		File fileyesterday = new File(filepathyesterday);
		File filetoday = new File(filepathtoday);

		try {
			BufferedReader bryesterday = new BufferedReader(new FileReader(fileyesterday));
			BufferedReader brtoday = new BufferedReader(new FileReader(filetoday));

			String text = "";

			while ((text = bryesterday.readLine()) != null) {
				String txtcode = text.split(" ")[1];
				if (txtcode.equals(code)) {
					yesterdayP = Double.parseDouble(text.split(" ")[0]);
					break;
				}
			}

			while ((text = brtoday.readLine()) != null) {
				String txtcode = text.split(" ")[1];
				if (txtcode.equals(code)) {
					todayP = Double.parseDouble(text.split(" ")[0]);
					break;
				}
			}

			brtoday.close();
			bryesterday.close();
			if (yesterdayP == 0) {
				return 1;
			}

			double a = Math.log(todayP / yesterdayP);
			return a;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}

	private void calculateFileName() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		yesterday += ".txt";
		this.filepathyesterday += yesterday;

		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 0);
		String today = new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());
		today += ".txt";
		this.filepathtoday += today;

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
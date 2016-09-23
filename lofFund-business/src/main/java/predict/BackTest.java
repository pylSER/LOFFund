package predict;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import blService.BackTestService;
import data.RealValueGetter;
import vo.BackTestVO;

public class BackTest implements BackTestService{

	public ArrayList<BackTestVO> backTest(String codeNumber, int days) {
		// get real p
		RealValueGetter realValue = new RealValueGetter();
		double[] real_p = realValue.getRecentValue(codeNumber, days + 1);
		// predict r
		PredictR pr = new PredictR();
		double[] r = pr.predictR(codeNumber, days + 1);

		// 使用默认时区和语言环境获得一个日历
		Calendar cal = Calendar.getInstance();
		// 定义格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 取当前日期的前days天
		cal.add(Calendar.DAY_OF_MONTH, -days + 1);

		String date = null;
		ArrayList<BackTestVO> list = new ArrayList<BackTestVO>();

		for (int i = 0; i < days; i++) {
			// get date
			date = format.format(cal.getTime());
			// calculate forecast p
			double p = real_p[i] * Math.exp(r[i]);
			list.add(new BackTestVO(p, real_p[i + 1], date));
			// date+1
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
	}
}

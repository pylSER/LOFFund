package bl;

import java.util.ArrayList;

import vo.FundDateDataVO;

public class RateCalculator {
	//e.g. 1.66,0.12%
	public String getYesterdayRateAndPrice(String code){
		FundInfoGetter getter =new FundInfoGetter();
		ArrayList<FundDateDataVO> list=getter.getFundInfo(code, 2); 
		FundDateDataVO yesterday=list.get(0);
		FundDateDataVO qiantian=list.get(1);
		double rate=(yesterday.getClose()-qiantian.getClose())/qiantian.getClose();
		rate=rate*100;
		String p=String.format("%.3f",rate);
		return yesterday.getClose()+","+p+"%";
	}
}




/**
 * 
 * @param code
 *            一只基金代码
 * @param days
 *            天数
 * @return 按日期降序返回，最近一天在最前面
 */
//public ArrayList<FundDateDataVO> getFundInfo(String code, int days) 
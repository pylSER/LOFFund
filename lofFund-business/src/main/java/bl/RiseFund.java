package bl;

import java.util.ArrayList;

import data.RealValueGetter;
import dataService.LatestDataGetterService;
import predict.PredictR;
import serialization.FundNameGetter;
import serialization.LatestDataGetter;
import vo.RiseVO;

/**
 * 获得可能实现套利的基金
 * 
 * @author sony
 *
 */
public class RiseFund {

	private double target = 0.006;
	private double trade = 50000;

	/**
	 * 
	 * @return 所有可能实现套利的基金
	 */
	public ArrayList<RiseVO> getRiseFund() {
		// return type
		ArrayList<RiseVO> list = new ArrayList<RiseVO>();

		// get code and name list
		FundNameGetter fund = new FundNameGetter();
		ArrayList<String> codeList = fund.getFundNameAndCode();
		// predict r
		PredictR pr = new PredictR();
		// get real p
		RealValueGetter real = new RealValueGetter();

		String code = null;
		double r = 0;
		double[] p = null;
		double pre = 0;

		LatestDataGetterService latestData = new LatestDataGetter();
		for (String s : codeList) {
			code = s.substring(2, 8);
			r = pr.predict(code);
			// not empty and the price rises
			p = real.getRecentValue(code, 1);
			pre = p[0] * Math.exp(r);
			double[] latest = latestData.getLatestData(code);
			if (pre > latest[1] * (1 + target) && latest[2] > trade)
				list.add(new RiseVO(code, s.substring(8), pre, latest[1] * (1 + target)));
		}
		code = null;
		p = null;
		latestData = null;
		return list;
	}
	
	
	
	public ArrayList<Boolean> isFundProfit(ArrayList<String> codelist) {
		ArrayList<RiseVO> riseVOs=getRiseFund();
		ArrayList<Boolean> reslist=new ArrayList<Boolean>();
		
		boolean isin=false;
		for (String code : codelist) {
			for (RiseVO riseVO : riseVOs) {
				if (riseVO.getCode().equals(code)) {
					isin=true;
					break;
				}
			}
			if (isin) {
				reslist.add(true);
			}else {
				reslist.add(false);
			}
			isin=false;
		}
		
		return reslist;
		
	}
	
}

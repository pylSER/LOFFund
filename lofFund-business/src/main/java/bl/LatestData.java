package bl;

import java.util.ArrayList;

import data.RealValueGetter;
import dataService.LatestDataGetterService;
import predict.PredictR;
import serialization.LatestDataGetter;
import vo.LatestDataVO;

public class LatestData {

	public LatestDataVO getLatestData(String codeNumber) {
		FundNameAndCodeGetter nameCode = new FundNameAndCodeGetter();
		ArrayList<String> list = nameCode.getFundNameAndCode();

		String name = "";
		for (String s : list) {
			if (s.substring(2, 8).equals(codeNumber)) {
				name = s.substring(8);
				break;
			}
		}

		LatestDataGetterService latest = new LatestDataGetter();
		double[] late = latest.getLatestData(codeNumber);

		PredictR pr = new PredictR();
		double r = pr.predict(codeNumber);
		RealValueGetter pReal = new RealValueGetter();
		double[] p = pReal.getRecentValue(codeNumber, 1);
		p[0]=p[0]*Math.exp(r);

		return new LatestDataVO(codeNumber, name, late[1], p[0], late[2]);
	}
}

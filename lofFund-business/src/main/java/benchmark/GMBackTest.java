package benchmark;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import blService.GMBackTestService;
import blService.GMPredictService;
import data.FundDataGetterImpl;
import dataService.FundDataGetterService;
import po.FundDateDataPO;
import vo.BackTestVO;

public class GMBackTest implements GMBackTestService {

	/**
	 * 
	 * @param days
	 * @return 获得days天回测大盘的数据，按日期升序，最近的在最后
	 */
	public ArrayList<BackTestVO> backTest(int days) {
		try {
			FundDataGetterService fund = new FundDataGetterImpl();
			List<FundDateDataPO> list = fund.getData("sh000001", days);
			GMPredictService pred = new GMPredict();
			double[] p = pred.predict(days);

			ArrayList<BackTestVO> arrList = new ArrayList<BackTestVO>();
			for (int i = 0; i < days; i++) {
				FundDateDataPO po = list.get(days - i - 1);
				arrList.add(new BackTestVO(p[i], po.close, po.date));
			}

			return arrList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<BackTestVO>();
		}
	}
}

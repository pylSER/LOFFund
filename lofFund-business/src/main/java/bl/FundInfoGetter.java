package bl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import data.FundDataGetterImpl;
import dataService.FundDataGetterService;
import po.FundDateDataPO;
import vo.FundDateDataVO;

public class FundInfoGetter {

	/**
	 * 
	 * @param code
	 *            一只基金代码
	 * @param days
	 *            天数
	 * @return 按日期降序返回，最近一天在最前面
	 */
	public ArrayList<FundDateDataVO> getFundInfo(String code, int days) {
		ArrayList<FundDateDataVO> list = new ArrayList<FundDateDataVO>();
		try {
			FundDataGetterService fund = new FundDataGetterImpl();   
			if (code.startsWith("16"))
				code = "sz" + code;
			else
				code = "sh" + code;
			List<FundDateDataPO> poList = fund.getData(code, days);
			for (FundDateDataPO po : poList) {
				list.add(new FundDateDataVO(po.close, po.code, po.date, po.high, po.low, po.open, po.r));
			}
			return list;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}

	}
}

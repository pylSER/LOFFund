package data;

import dataService.FundDataGetterService;
import po.FundDateDataPO;
import tool.DataSuperClass;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Qiang
 * @date 8/30/16
 */
public class FundDataGetterImpl extends DataSuperClass implements FundDataGetterService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3279408794152385533L;
	private final String tableName = "fund";

	public FundDataGetterImpl() throws RemoteException {
		super();
	}

	/**
	 * 返回按日期降序的po序列
	 */
	public List<FundDateDataPO> getData(String codeNumber, int days) {

		String sql = "SELECT * FROM `fund` where code =" + "\"" + codeNumber + "\"" + " order by date desc limit "
				+ days;
		// System.out.println(sql);
		List<FundDateDataPO> pos = new ArrayList<FundDateDataPO>();
		try {
			preState = conn.prepareStatement(sql);
			result = preState.executeQuery();
			FundDateDataPO po;
			while (result.next()) {
				po = new FundDateDataPO();

				po.date = result.getString(1);
				po.code = result.getString(2);
				po.open = result.getDouble(3);
				po.close = result.getDouble(4);
				po.high = result.getDouble(5);
				po.low = result.getDouble(6);
				po.r = result.getDouble(7);
				pos.add(po);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pos;
	}
}

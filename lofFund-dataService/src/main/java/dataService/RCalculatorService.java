package dataService;

public interface RCalculatorService {

	/**
	 * 
	 * @param codeNumber
	 *            股票或基金的代码串，以","连接，如"399372.SZ,399373.SZ,399376.SZ,399377.SZ,399005.SZ"
	 * @return 返回最近一天的R，如果当天还没有收盘返回-infinite;获取失败返回空串.一个codeNumber对应二维数组的一行，每一列表示一天的数据，最近一天在最后一列
	 */
	public double[][] getR(String codeNumber);
	
	/**
	 * 
	 * @param codeNumber
	 *            股票或基金的代码串，以","连接，如"399372.SZ,399373.SZ,399376.SZ,399377.SZ,399005.SZ"
	 * @param startDate
	 * @param endDate
	 * @return 返回startDate+1到endDate的R;获取失败返回空串.一个codeNumber对应二维数组的一行，每一列表示一天的数据，最近一天在最后一列
	 */
	public double[][] getR(String codeNumber, String startDate, String endDate);
}

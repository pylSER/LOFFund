package dataService;

public interface TimeSeriesService {

	/**
	 * 
	 * @param codeNumber
	 *            股票或基金的代码串，以","连接，如"399372.SZ,399373.SZ,399376.SZ,399377.SZ,399005.SZ"
	 * @param type
	 *            希望获得的返回类型，如open、high、low、close等
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 返回type型数据串，按日期升序，最近的在最后;获取失败返回空串.一个codeNumber对应二维数组的一行，每一列表示一天的数据
	 *         ，最近一天在最后一列
	 */
	public double[][] getTimeSeriesData(String codeNumber, String type, String startDate, String endDate);
}

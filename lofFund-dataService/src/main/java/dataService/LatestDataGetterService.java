package dataService;

public interface LatestDataGetterService {

	/**
	 * 
	 * @param codeNumber 基金或股票代码
	 * @return 返回现价、买一价、成交量
	 */
	public double[] getLatestData(String codeNumber);
}

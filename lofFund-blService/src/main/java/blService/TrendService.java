package blService;

public interface TrendService {

	/**
	 * 
	 * @return 返回乐观、中立、悲观的占比,[乐观,中立,悲观]
	 */
	public double[] getTrend();
}

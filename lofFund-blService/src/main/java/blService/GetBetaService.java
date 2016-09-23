package blService;

public interface GetBetaService {

	/**
	 * 
	 * @param code
	 *            一个代码
	 * @param date
	 *            日期
	 * @param r
	 *            r值
	 * @return beta
	 */
	public double[] getBeta(String code, double left_r, double[] r);
}

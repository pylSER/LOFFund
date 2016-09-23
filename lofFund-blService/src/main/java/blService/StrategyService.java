package blService;

public interface StrategyService {

	/**
	 * 
	 * @param code
	 *            基金代码
	 * @return 基金对应的指数，没有返回林辉的"399372.SZ,399373.SZ,399376.SZ,399377.SZ,399005.SZ"
	 */
	public String getStrategy(String code);
}

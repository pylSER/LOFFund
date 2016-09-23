package bl;

import blService.StrategyService;
import serialization.StrategySerialization;

/**
 * 商院的人制定指数，形成策略，实现指数策略的存储和获取
 * 
 * @author 云奎
 *
 */
public class Strategy implements StrategyService {

	/**
	 * 
	 * @param code
	 *            基金代码
	 * @return 基金对应的指数，
	 *         没有返回林辉的"399372.SZ,399373.SZ,399376.SZ,399377.SZ,399005.SZ"
	 */
	public String getStrategy(String code) {
		StrategySerialization strategy = new StrategySerialization();
		return strategy.getStrategy(code);
	}
}

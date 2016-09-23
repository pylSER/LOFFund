package blService;

public interface GMPredictService {

	/**
	 * 预测明天大盘的值
	 * 
	 * @return 返回两个数[预测值，今天的值]
	 */
	public double[] predictTomorrow();

	/**
	 * 预测大盘，用于回测，由GMBackTestService调用，回测图不需要调该接口
	 * 
	 * @param days
	 * @return 获得days天预测大盘的数据，按日期升序，最近的在最后
	 */
	public double[] predict(int days);
}

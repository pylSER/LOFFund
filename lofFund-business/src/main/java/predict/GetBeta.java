package predict;

import java.util.Random;

import blService.GetBetaService;

public class GetBeta implements GetBetaService{
	// times of calculation
	private final int iter = 10000;

	public double[] getBeta(String code, double left_r, double[] r) {
		int n = iter;
		double best = Double.MAX_VALUE;
		double[] beta = null;
		while (n > 0) {
			double[] b = produceBeta(r.length,n);
			double left = train(r, b);
			if (Math.abs(left - left_r) < best) {
				best = Math.abs(left - left_r);
				beta = b;
			}
			n--;
		}
		
		if (beta == null)
			return new double[0];
		return beta;
	}

	/**
	 * 每一次计算一组beta，得到预测值
	 * 
	 * @param r
	 *            上级给出的r
	 * @param beta
	 *            随机生成的beta数组
	 * @return 预测值
	 */
	private double train(double[] r, double[] beta) {
		double left = 0;
		for (int i = 0; i < r.length; i++) {
			left += r[i] * beta[i];
		}
		return left;
	}

	/**
	 * 
	 * @param n
	 *            beta的个数
	 * @return 随机生成beta数组，和为1
	 */
	private double[] produceBeta(int n,int seed) {
		double[] beta = new double[n];
		Random rand = new Random(seed);
		double sum = 0;
		for (int i = 0; i < n - 1; i++) {
			beta[i] = rand.nextDouble() * 2 - 1;
			sum += beta[i];
		}
		beta[n - 1] = 1 - sum;
		return beta;
	}
}

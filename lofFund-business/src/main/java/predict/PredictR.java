package predict;

import java.rmi.RemoteException;
import java.util.List;

import bl.Strategy;
import blService.GetBetaService;
import blService.StrategyService;
import data.FundDataGetterImpl;
import data.LeftRGetter;
import dataService.FundDataGetterService;
import dataService.LatestDataGetterService;
import dataService.LeftRGetterService;
import po.FundDateDataPO;
import serialization.LatestDataGetter;

public class PredictR {

	/**
	 * 用于预测，回测请用predictR(String codeNumber, int days)
	 * 
	 * @param codeNumber
	 *            一只股票代码，如"600000"
	 * @return 用于预测
	 */
	public double predict(String codeNumber) {
		GetBetaService betaService = new GetBeta();
		// get real r
		LeftRGetterService leftR = new LeftRGetter();
		double[] left_r = leftR.getRecentLeftR(codeNumber, 1);

		// use r to calculate beta
		double[][] r = calculateR(codeNumber, 1);

		if (left_r.length > 0 && r[0].length > 0) {
			double[] beta = betaService.getBeta(codeNumber, left_r[0], r[0]);
			double[] latestR = calculateLatestR(codeNumber);
			return calculateLeftR(latestR, beta);
		} else {
			return 0;
		}
	}

	/**
	 * 用于回测，真实预测请用predict(String codeNumber)
	 * 
	 * @param codeNumber
	 *            一只股票代码，如"600000"
	 * @param days
	 *            天数
	 * @return 用于回测
	 */
	public double[] predictR(String codeNumber, int days) {

		GetBetaService betaService = new GetBeta();
		// get real r
		LeftRGetterService leftR = new LeftRGetter();
		double[] left_r = leftR.getRecentLeftR(codeNumber, days);

		// use r to calculate beta
		double[] predict = new double[days];
		double[][] r = calculateR(codeNumber, days);
		for (int i = 0; i < days; i++) {
			double[] beta = betaService.getBeta(codeNumber, left_r[days - i - 1], r[i]);
			predict[i] = calculateLeftR(r[i], beta);
		}

		return predict;

	}

	private double[] calculateLatestR(String codeNumber) {
		// get data in days+1
		FundDataGetterService fund = null;
		try {
			fund = new FundDataGetterImpl();

			// get strategy relating to codeNumber
			StrategyService strategy = new Strategy();
			String s = strategy.getStrategy(codeNumber);

			// split strategy like
			// "sz399372+sz399373+sz399376+sz399377+sz399005"
			String[] sp = new String[0];
			if (s.contains("+")) {
				sp = s.split("\\+");
			} else {
				sp = new String[1];
				sp[0] = s;
			}

			double[] r = new double[sp.length];
			// for every separated strategy code,get their data and calculate r
			LatestDataGetterService latestData = new LatestDataGetter();
			for (int i = 0; i < sp.length; i++) {
				List<FundDateDataPO> list = fund.getData(sp[i], 1);
				double[] latest = latestData.getLatestData(sp[i]);
				r[i] = Math.log(latest[0] / list.get(0).close);
			}
			return r;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new double[1];
		}
	}

	private double[][] calculateR(String codeNumber, int days) {
		// get data in days+1
		FundDataGetterService fund = null;
		try {
			fund = new FundDataGetterImpl();

			// get strategy relating to codeNumber
			StrategyService strategy = new Strategy();
			String s = strategy.getStrategy(codeNumber);

			// split strategy like
			// "sz399372+sz399373+sz399376+sz399377+sz399005"
			String[] sp = new String[0];
			if (s.contains("+")) {
				sp = s.split("\\+");
			} else {
				sp = new String[1];
				sp[0] = s;
			}

			double[][] r = new double[days][sp.length];
			// for every separated strategy code,get their data and calculate r
			for (int i = 0; i < sp.length; i++) {
				List<FundDateDataPO> list = fund.getData(sp[i], days + 1);
				int len = list.size() - 1;
				for (int j = len; j > 0; j--) {
					r[len - j][i] = Math.log(list.get(j - 1).close / list.get(j).close);
				}
			}
			return r;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new double[days][0];
		}
	}

	/**
	 * 计算一组beta，得到预测值
	 * 
	 * @param r
	 *            上级给出的r
	 * @param beta
	 *            随机生成的beta数组
	 * @return 预测值
	 */
	private double calculateLeftR(double[] r, double[] beta) {
		double left = 0;
		int len = Math.min(r.length, beta.length);
		for (int i = 0; i < len; i++) {
			left += r[i] * beta[i];
		}
		return left;
	}
}

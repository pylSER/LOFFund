package benchmark;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import blService.GMPredictService;
import data.FundDataGetterImpl;
import dataService.FundDataGetterService;
import po.FundDateDataPO;

/**
 * 预测大盘
 * 
 * @author 云奎
 *
 */
public class GMPredict implements GMPredictService {

	/**
	 * 输入节点数
	 */
	private int input = 4;
	/**
	 * 取第T个数
	 */
	private int T = 2;
	/**
	 * 偏移量
	 */
	private int b = 1;

	/**
	 * 预测明天大盘的值
	 * 
	 * @return 返回两个数[预测值，今天的值]
	 */
	public double[] predictTomorrow() {
		try {
			FundDataGetterService fund = new FundDataGetterImpl();
			List<FundDateDataPO> list = fund.getData("sh000001", 1);
			FundDateDataPO po = list.get(0);

			// 获得今天日期
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String today = format.format(cal.getTime());
			// 最新一天日期
			String date = po.date;

			double[] pReal = new double[2];
			double[] p = null;
			if (date.equals(today))
				p = predict(1, T, 1);
			else
				p = predict(1, T + b, 1);
			if (p[0] > 0)
				pReal[0] = p[0];
			else
				pReal[0] = po.close;
			pReal[1] = po.close;

			return pReal;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new double[2];
		}
	}

	/**
	 * 预测大盘，用于回测
	 * 
	 * @param days
	 * @return 获得days天预测大盘的数据，按日期升序，最近的在最后
	 */
	public double[] predict(int days) {
		return predict(days, T, 0);
	}

	private double[] predict(int days, int T, int b) {
		int len = days + input - b;
		try {
			FundDataGetterService fund = new FundDataGetterImpl();
			List<FundDateDataPO> list = fund.getData("sh000001", len);

			// convert arraylist of close price into array in order of increase
			double[] fs = new double[len];
			int index = len - 1;
			for (FundDateDataPO po : list) {
				fs[index] = po.close;
				index--;
			}

			// predict close price
			double[] predict = new double[days];
			for (int i = 0; i < days; i++) {
				double p = getOutput(fs, input, i, T);
				if (p > 0)
					predict[i] = p;
				else
					predict[i] = fs[input + i];
			}

			return predict;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new double[days];
		}
	}

	private double getOutput(double[] fs, int len, int start, int T) {
		// 预测模型函数
		int size = len;
		int tsize = len - 1;
		double[] arr = fs;// 原始数组
		double[] arr1 = new double[size];// 经过一次累加数组
		double sum = 0;
		for (int i = 0; i < size; i++) {
			sum += arr[start + i];
			arr1[i] = sum;
		}

		double[] arr2 = new double[tsize];// arr1的紧邻均值数组
		for (int i = 0; i < tsize; i++) {
			arr2[i] = (arr1[i] + arr1[i + 1]) / 2.0;
		}
		/*
		 * 下面建立 向量B和YN求解待估参数向量， 即求参数a,b 下面建立向量B, B是5行2列的矩阵， 相当于一个二维数组。
		 */
		double[][] B = new double[tsize][2];
		for (int i = 0; i < tsize; i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 1)
					B[i][j] = 1;
				else
					B[i][j] = -arr2[i];
			}

		}
		/*
		 * 下面建立向量YN
		 */
		double[][] YN = new double[tsize][1];
		for (int i = 0; i < tsize; i++) {
			for (int j = 0; j < 1; j++) {
				YN[i][j] = arr[start + i + 1];
			}
		}

		/*
		 * B的转置矩阵BT,2行5列的矩阵
		 */
		double[][] BT = new double[2][tsize];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < tsize; j++) {
				BT[i][j] = B[j][i];
			}
		}
		/*
		 * 将BT和B的乘积所得到的结果记为数组B2T,则B2T是一个2*2的矩阵
		 */
		double[][] B2T = new double[2][2];
		for (int i = 0; i < 2; i++) {// rows of BT
			for (int j = 0; j < 2; j++) {// cloums of B
				for (int k = 0; k < tsize; k++) {// cloums of BT=rows of B
					B2T[i][j] = B2T[i][j] + BT[i][k] * B[k][j];
				}
			}
		}
		/* 下面求B2T的逆矩阵，设为B_2T，怎么适用于一般的矩阵？ */
		double[][] B_2T = new double[2][2];
		B_2T[0][0] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0])) * B2T[1][1];
		B_2T[0][1] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0])) * (-B2T[0][1]);
		B_2T[1][0] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0])) * (-B2T[1][0]);
		B_2T[1][1] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0])) * B2T[0][0];
		/*
		 * 根据以上所求的各已知量下面求待估参数的未知量a和b，待估向量矩阵等于B_2T*BT*YN
		 * 下面我们分别求这些矩阵的乘积，首先求B_2T*BT，令B_2T*BT的乘积为A矩阵，则A就是一个2*5的矩阵 下面先求A矩阵
		 */
		double[][] A = new double[2][tsize];
		for (int i = 0; i < 2; i++) {// rows of B_2T
			for (int j = 0; j < tsize; j++) {// cloums of BT
				for (int k = 0; k < 2; k++) {// cloums of B_2T=rows of BT
					A[i][j] = A[i][j] + B_2T[i][k] * BT[k][j];
				}
			}
		}
		/*
		 * 下面求A和YN矩阵的乘积，令乘积为C矩阵，则C就是一个2*1的矩阵
		 */
		double[][] C = new double[2][1];
		for (int i = 0; i < 2; i++) {// rows of A
			for (int j = 0; j < 1; j++) {// cloums of YN
				for (int k = 0; k < tsize; k++) {// cloums of A=rows of YN
					C[i][j] = C[i][j] + A[i][k] * YN[k][j];
				}
			}
		}
		/* 根据以上所得则a=C[0][0],b=C[1][0]; */
		double a = C[0][0], b = C[1][0];
		if (a == 0)
			return 0;

		int i = T;// 读取一个数值
		return (arr[start] - b / a) * Math.exp(-a * (i + 1)) - (arr[start] - b / a) * Math.exp(-a * i);
	}
}

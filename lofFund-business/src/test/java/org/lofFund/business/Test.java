//package org.lofFund.business;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//
//import org.junit.After;
//
//import data.RealValueGetter;
//import dataService.LatestDataGetterService;
//import predict.PredictR;
//import serialization.FundNameGetter;
//import serialization.LatestDataGetter;
//import serialization.StrategySerialization;
//import vo.RiseVO;
//
//public class Test {
//	private String[] s={"sz399303","sz399316","sz399401","sz399311","sz399315","sz399400","sz399312"
//			,"sz399314","sz399310","sz399313","sz399374","sz399375","sh513500"};
//	private int[] ans=new int[14];
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	public void chai(int k) {
//		for (int i=0;i<14;i++){
//			ans[i]=0;
//		}
//		int length=0;
//		while (k>0){
//			ans[length]=k%2;
//			k=k/2;
//			length++;
//		}
//	}
//	@org.junit.Test
//	public void test() {
//		chai(14);
////		for (int i=0;i<13;i++){System.out.print(ans[i]);}System.out.println();
//		for (int i=1;i<8192;i++){
//			System.out.println(i);		
//			StrategySerialization.str="";
//			chai(i);
////			for (int w=0;w<13;w++){System.out.print(ans[w]);}
//			int hash=1;
//			for (int j=0;j<13;j++){
//				if (ans[j]==1){
//					if (hash==0){
//						StrategySerialization.str+="+";
//					}
//					StrategySerialization.str+=s[j];					
//					hash=0;
//				}
//				
//			}
//		   System.out.println(StrategySerialization.str);		
//		
//		// return type
//		ArrayList<RiseVO> list = new ArrayList<RiseVO>();
//
//		// get code and name list
//		FundNameGetter fund = new FundNameGetter();
//		ArrayList<String> codeList = fund.getFundNameAndCode();
//		// predict r
//		PredictR pr = new PredictR();
//		// get real p
//		RealValueGetter real = new RealValueGetter();
//
//		String code = null;
//		double r = 0;
//		double[] p = null;
//		double pre = 0;
//
//		LatestDataGetterService latestData = new LatestDataGetter();
//		// get real p
//		RealValueGetter realValue = new RealValueGetter();
//		double mean = 0;
//		double var = 0;
//		double max = Double.MIN_VALUE;
//		double min = Double.MAX_VALUE;
//		String maxCode = null;
//		String minCode = null;
//		double[] alist = new double[codeList.size()];
//		int index = 0;
//
//		for (String s : codeList) {
//			code = s.substring(2, 8);
//			r = pr.predict(code);
//			// not empty and the price rises
//			p = real.getRecentValue(code, 2);
//			pre = p[0] * Math.exp(r);
//			double diff = pre - p[1];
//			if (diff < min) {
//				min = diff;
//				minCode = s;
//			}
//			if (diff > max) {
//				max = diff;
//				maxCode = s;
//			}
//			mean += diff;
//			alist[index] = diff;
//			index++;
//		}
//		mean /= codeList.size();
//		for (int i2 = 0; i2 < alist.length; i2++) {
//			var += (alist[i2] - mean) * (alist[i2] - mean);
//		}
//		var /= (codeList.size() - 1);
//		System.out.println(
//				"均值 " + mean + " " + "方差 " + var + "最大值 " + maxCode + " " + max + " " + "最小值 " + minCode + " " + min);
//	}
//	}
//}

package org.lofFund.business;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import bl.FundNameAndCodeGetter;
import predict.PredictR;

public class PredictTest {

	@Test
	public void test() {
//		PredictR pr = new PredictR();
//		double p1 = pr.predict("166904");
//		double[] p2 = pr.predictR("166904", 1);
//		System.out.println(p1);
//		for(int i = 0;i<p2.length;i++){
//			System.out.println(p2[i]);
//		}
		FundNameAndCodeGetter fgetter=new FundNameAndCodeGetter();
		ArrayList<String> codelist=new ArrayList<String>();
		codelist=fgetter.getFundNameAndCode();
		System.out.print(codelist.get(0).substring(9, 13));
	}

}

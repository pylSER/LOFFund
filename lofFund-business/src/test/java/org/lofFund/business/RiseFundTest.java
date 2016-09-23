package org.lofFund.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import bl.RiseFund;
import vo.RiseVO;

public class RiseFundTest {

	@Test
	public void test() {
		RiseFund r = new RiseFund();
		ArrayList<RiseVO> list = r.getRiseFund();

		for (RiseVO vo : list) {
			System.out.println(vo.getCode() + " " + vo.getName().trim() + " " + vo.getAsk1() + " " + vo.getPredict());
		}
		System.out.println(list.size());
		
		
		
		ArrayList<String> codelist=new ArrayList<String>();
		codelist.add("502053");
		codelist.add("1123");
		codelist.add("160225");
		codelist.add("164810");
		
		System.out.println(r.isFundProfit(codelist));
		
		
//		Collections.sort(list);
//		
//		for (RiseVO vo : list) {
//			System.out.println(vo.getCode() + " " + vo.getName().trim() + " " + vo.getAsk1() + " " + vo.getPredict());
//		}
//		System.out.println(list.size());	
	}
}

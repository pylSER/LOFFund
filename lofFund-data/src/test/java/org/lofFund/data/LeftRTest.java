package org.lofFund.data;

import static org.junit.Assert.*;

import org.junit.Test;

import data.LeftRGetter;
import dataService.LeftRGetterService;

public class LeftRTest {

	@Test
	public void test() {
		LeftRGetterService r = new LeftRGetter();
		double[] r1 = r.getRecentLeftR("166904", 2);
		System.out.println(r1[0]);
	}

}

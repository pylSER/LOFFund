package org.lofFund.data;

import static org.junit.Assert.*;

import org.junit.Test;

import dataService.LatestDataGetterService;
import serialization.LatestDataGetter;

public class LatestDataTest {

	@Test
	public void test() {
		LatestDataGetterService l= new LatestDataGetter();
		double[] r =l.getLatestData("sz399372");
		System.out.println(r[0]);
	}

}

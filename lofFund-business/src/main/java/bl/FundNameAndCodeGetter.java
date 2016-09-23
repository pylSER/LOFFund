package bl;

import java.util.ArrayList;

import serialization.FundNameGetter;

public class FundNameAndCodeGetter {

	public ArrayList<String> getFundNameAndCode(){
		FundNameGetter fund = new FundNameGetter();
		return fund.getFundNameAndCode();
	}
}

package vo;

public class SearchVO {
	private String code;
	private String name;
	private String yesterdayPrice;
	private String yesterdayRate;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		
	} 
	public String getYesterdayPrice() {
		return yesterdayPrice;
	}
	public void setYesterdayPrice(String yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}
	public String getYesterdayRate() {
		return yesterdayRate;
	}
	public void setYesterdayRate(String yesterdayRate) {
		this.yesterdayRate = yesterdayRate;
	}
	
	@Override
	public String toString(){
		String reString=code+"*"+name;
		reString+="*";
		reString+=yesterdayPrice;
		reString+="*";
		reString+=yesterdayRate;
		return reString;
	}
}

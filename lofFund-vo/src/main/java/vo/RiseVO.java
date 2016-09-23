package vo;

public class RiseVO implements Comparable<RiseVO>{

	private String code;
	private String name;
	private double predict;
	/**
	 * 买一价
	 */
	private double ask1;

	public RiseVO(String code,String name,double predict,double ask1){
		this.code = code;
		this.name = name;
		this.predict = predict;
		this.ask1 = ask1;
	}

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

	public double getPredict() {
		return predict;
	}

	public void setPredict(double predict) {
		this.predict = predict;
	}
	
	/**
	 * 买一价
	 */
	public double getAsk1() {
		return ask1;
	}

	/**
	 * 买一价
	 */
	public void setAsk1(double ask1) {
		this.ask1 = ask1;
	}

	public int compareTo(RiseVO arg0) {
		double thisval=this.predict-this.ask1;
		double thatval=arg0.predict-arg0.ask1;
		
		if(thisval>thatval)
			return 1;
		if(thisval<thatval)
			return -1;
		else{
			return 0;
		}
	}
}

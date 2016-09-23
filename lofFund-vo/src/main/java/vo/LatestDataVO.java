package vo;

public class LatestDataVO {

	private String code;
	private String name;
	private double ask1;
	private double predict;
	private double volume;

	public LatestDataVO(String code, String name, double ask1, double predict, double volume) {
		super();
		this.code = code;
		this.name = name;
		this.ask1 = ask1;
		this.predict = predict;
		this.volume = volume;
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

	public double getAsk1() {
		return ask1;
	}

	public void setAsk1(double ask1) {
		this.ask1 = ask1;
	}

	public double getPredict() {
		return predict;
	}

	public void setPredict(double predict) {
		this.predict = predict;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

}

package vo;

public class BackTestVO {

	private double predict;
	private double real;
	private String date;

	public BackTestVO(double predict, double real, String date) {
		this.predict = predict;
		this.real = real;
		this.date = date;
	}

	public double getPredict() {
		return predict;
	}

	public void setPredict(double predict) {
		this.predict = predict;
	}

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}

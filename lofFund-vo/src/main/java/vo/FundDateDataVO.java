package vo;

public class FundDateDataVO {
	private String code;
	private String date;
	private double open;
	private double close;
	private double high;
	private double low;
	private double v;

	public FundDateDataVO(double close, String code, String date, double high, double low, double open, double v) {
		this.close = close;
		this.code = code;
		this.date = date;
		this.high = high;
		this.low = low;
		this.open = open;
		this.v = v;
	}

	public FundDateDataVO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}

	
}

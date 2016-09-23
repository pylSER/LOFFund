package po;

/**
 * 基金日数据
 * 
 * @author Qiang
 * @date 8/28/16
 */
public class FundDateDataPO {
	public String code;
	public String date;
	public double open;
	public double close;
	public double high;
	public double low;
	public double r;

	public FundDateDataPO(double close, String code, String date, double high, double low, double open, double r) {
		this.close = close;
		this.code = code;
		this.date = date;
		this.high = high;
		this.low = low;
		this.open = open;
		this.r = r;
	}

	public FundDateDataPO() {
		// TODO Auto-generated constructor stub
	}
}

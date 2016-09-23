package text;

public class test {

	public static void main(String[] args) {
		Trend t = new Trend();
		double[] tr = t.getTrend();
		System.out.print(tr[0] + " " + tr[1] + " " + tr[2]);
	}
}

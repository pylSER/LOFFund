package tool;

public class DataTranslate {

	/**
	 * 
	 * @param numberOfCode 
	 * @param data
	 * @return
	 */
	public double[][] translate(int numberOfCode, double[] data) {
		int total = data.length;
		int num = total / numberOfCode;
		double[][] result = new double[numberOfCode][num];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = data[j + i * num];
			}
		}
		return result;
	}
}

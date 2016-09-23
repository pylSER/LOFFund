package dataService;

public interface LeftRGetterService {
	public double getLeftR(String code);
	public double getLeftRByDate(String code, String date);
	public double[] getRecentLeftR(String code,int days);//获得最近days天的LeftR 第一个是最近一天的数据
	
}

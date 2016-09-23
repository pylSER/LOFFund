package blService;

import java.util.ArrayList;

import vo.BackTestVO;

public interface GMBackTestService {

	/**
	 * 
	 * @param days
	 * @return 获得days天回测大盘的数据，按日期升序，最近的在最后
	 */
	public ArrayList<BackTestVO> backTest(int days);
}

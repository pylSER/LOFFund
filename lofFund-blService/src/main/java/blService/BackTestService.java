package blService;

import java.util.ArrayList;

import vo.BackTestVO;

public interface BackTestService {
	public ArrayList<BackTestVO> backTest(String codeNumber, int days);
}

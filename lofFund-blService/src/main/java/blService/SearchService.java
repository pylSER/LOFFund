package blService;

import java.util.ArrayList;
import vo.SearchVO;

public interface SearchService {
	public ArrayList<SearchVO> getSearchInfo(String input,String type);
	public String getName(String code);
}

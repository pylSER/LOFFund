package dataService;

import po.FundDateDataPO;

import java.util.List;

/**
 * @author Qiang
 * @date 8/30/16
 */
public interface FundDataGetterService {

    /**
     *
     *@return 每一天的数据包装成一个po
     * @param codeNumber 单个股票或基金代码，每次只能拿一只股票的数据
     * @param days 拿最近days天的数据，升序或降序随意
     */
    List<FundDateDataPO> getData(String codeNumber, int days);

}

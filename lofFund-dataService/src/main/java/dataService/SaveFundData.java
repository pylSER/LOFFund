package dataService;

import java.sql.Date;

/**
 * @author Qiang
 * @date 8/28/16
 */
public interface SaveFundData {
    /**
     * 更新一段时间内的基金数据并写入数据库
     * @param start
     * @param end
     * @return
     */
    boolean updateFundData(String start, String end);

    /**
     * 更新某天的基金数据并写入数据库
     * @param date
     * @return
     */
    boolean updateFundData(String date);

}

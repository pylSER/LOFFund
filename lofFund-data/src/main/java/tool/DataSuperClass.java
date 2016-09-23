package tool;

/**
 * @author Qiang
 * @date 8/28/16
 */
import po.FundDateDataPO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * 所有数据实现层的父类
 *
 * @author czq
 * @version 2015年10月31日 上午9:18:30
 */
public class DataSuperClass extends UnicastRemoteObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 与数据库的连接
     */
    protected Connection conn;
    /**
     *
     */
    protected PreparedStatement preState;
    /**
     * 数据库语句
     */
    protected String sql;
    /**
     * 数据库操作影响结果集
     */
    protected ResultSet result;
    /**
     * 查找返回的消息
     */
    protected ArrayList<String> findMes;
    /**
     * 在数据库操作中影响到的行数（信息条数）
     */
    protected int affectRows;


    private static final Map<String, ArrayList<String>> SQLmap = new HashMap<String, ArrayList<String>>(50);



    public DataSuperClass() throws RemoteException {
        this.conn = DataBaseInit.getConnection();
    }



    protected String bulidAddSQL(String name, int paraNum) {
        StringBuffer buffer = new StringBuffer("INSERT INTO `" + name
                + "` VALUES (");
        for (int i = 0; i < paraNum - 1; i++) {
            buffer.append(" ? ,");
        }

        buffer.append("? )");

        return buffer.toString();
    }

    protected String bulidDelSQL(String name, String primaryKey) {
        return "DELETE FROM `" + name + "` WHERE " + primaryKey + " =";
    }

    protected String bulidFindSQL(String name, String primaryKey) {

        return "SELECT * FROM `" + name + "` WHERE " + primaryKey + " = ";

    }

    protected String bulidUpdateSQL(String name, int paraNum, String... paras) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE `").append(name).append("` SET ");
        for (int i = 0; i < paraNum - 1; i++) {
            buffer.append(paras[i + 1]).append('=').append(" ? ,");
        }
        buffer.deleteCharAt(buffer.length() - 1);

        buffer.append("WHERE " + paras[0] + " = ");

        return buffer.toString();
    }



    /**
     * 辅助方法，实现将数组转化为字符串
     *
     * @return
     */
    public String tranFromArrayToString(String[] datas) {
        if(datas == null){
            return null;
        }
        StringBuffer buffer = new StringBuffer(datas.length * 15);
        for (String data : datas) {
            buffer.append(data).append(',');
        }
        return buffer.toString();
    }

    public String tranFromArrayToString(ArrayList<String> datas) {
        if(datas == null){
            return null;
        }

        StringBuffer buffer = new StringBuffer(datas.size() * 15);
        for (String data : datas) {
            buffer.append(data).append(',');
        }
        return buffer.toString();
    }

    public String[] tranFromStringToArray(String data) {
        String[] temp = data.split(",");
        return (temp.length == 0) ? null : temp;
    }

    public ArrayList<String> tranFromStringToArrayList(String data) {
        if(data == null){
            return null;
        }
        ArrayList<String> result;
        String[] temp = tranFromStringToArray(data);
        if (temp != null) {
            result = new ArrayList<String>(temp.length);
            Collections.addAll(result, temp);
            return result;
        } else {
            return null;
        }
    }

}
package serialization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import tool.IOHelper;

/**
 * 实现指数的序列化存储和获取
 * 
 * @author 云奎
 *
 */
public class StrategySerialization {

	/**
	 * 存<code#indexNumber>,code是基金代码,indexNumber是指数串，以"+"连接，
	 * 如"166904#sz399372+sz399373+sz399376+sz399377+sz399005"
	 */
	private final String filePath = "D:\\Competiton\\花旗杯\\lofdata\\config.txt";

	/**
	 * 
	 * @param code
	 *            基金代码
	 * @return 基金对应的指数， 没有返回林辉的"399372+sz399373+sz399376+sz399377+sz399005"
	 */
	public String getStrategy(String code) {
		File currtimefile = new File(filePath);
		BufferedReader strategy = null;
		try {
			strategy = new BufferedReader(new FileReader(currtimefile));
			String text = null;
			text = strategy.readLine();
			strategy.close();
			if (text == null)
				return "sz399372+sz399373+sz399376+sz399377+sz399005";
			else
				return text;
		} catch (Exception e) {
			e.printStackTrace();
			return "sz399303+sz399316+sz399401+sz399311+sz399315+sz399400+sz399312+sz399314+sz399310+sz399313";
		}
	}
}

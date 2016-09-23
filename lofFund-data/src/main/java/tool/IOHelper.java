package tool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class IOHelper {
	
	/**
	 * 
	 * @param filepath 文件路径
	 * @param obj 写入对象
	 * @throws IOException
	 */
	public void writeToDisk(String filepath,Object obj) throws IOException{
		FileOutputStream fos=new FileOutputStream(filepath,false);
		ObjectOutputStream oos =new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();	
	}
	
	/**
	 * 
	 * @param filepath 文件路径
	 * @return 读出对象
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object readFromDisk(String filepath) throws IOException, ClassNotFoundException{
		FileInputStream fis=new FileInputStream(filepath);
		ObjectInputStream ois =new ObjectInputStream(fis);
		Object obj=ois.readObject();
		ois.close();
		return obj;

	}

	public static List<String> getFileData(String filepath) throws FileNotFoundException {
		List<String> result = new ArrayList<String>(2000);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
			String line = reader.readLine();
			while (line != null){
				result.add(line);
				line = reader.readLine();
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}


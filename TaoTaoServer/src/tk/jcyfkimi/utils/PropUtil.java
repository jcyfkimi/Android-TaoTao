package tk.jcyfkimi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PropUtil {

	public static Map<String ,String> getDBProps() throws IOException{
		Map<String ,String> map = new HashMap<String,String>();
		InputStream is = PropUtil.class.getResourceAsStream("/db.properties");
		InputStreamReader inputStreamReader = new InputStreamReader(is);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = bufferedReader.readLine();
		while(line != null){
			String [] items = line.split("=");
			map.put(items[0], items[1]);
			line = bufferedReader.readLine();
		}
		return map;
	}
}

package tk.jcyfkimi.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import tk.jcyfkimi.utils.PropUtil;

public class ConnDB {

	private Connection conn = null;
	public Connection getConn(){
		try {
			PropUtil propUtil = new PropUtil();
			Map<String,String> map = propUtil.getDBProps();
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(map.get("url"),map.get("user"),map.get("pass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}

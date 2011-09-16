package tk.jcyfkimi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tk.jcyfkimi.beans.Goods;
import tk.jcyfkimi.db.ConnDB;

/**
 * 查询数据库，得到所有的goods集合。
 * 
 * @author Administrator
 * 
 */
public class GoodsDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ConnDB cd = null;

	private void init() {
		cd = new ConnDB();
		conn = cd.getConn();
	}

	public List<Goods> getAllGoods() {
		List<Goods> list = new ArrayList<Goods>();
		init();
		String sql = "select gid,gname,gstorage,gprice,guid from goods";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods(rs.getInt("gid"),
						rs.getString("gname"), rs.getInt("gstorage"), rs
								.getDouble("gprice"), rs.getInt("guid"));
				list.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

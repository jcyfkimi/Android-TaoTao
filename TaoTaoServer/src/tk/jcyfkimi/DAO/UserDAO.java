package tk.jcyfkimi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tk.jcyfkimi.beans.UpdateUserInfo;
import tk.jcyfkimi.beans.User;
import tk.jcyfkimi.db.ConnDB;

public class UserDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ConnDB cd = null;

	private void init() {
		cd = new ConnDB();
		conn = cd.getConn();
	}

	/**
	 * 更新用户信息
	 * 
	 * @param userInfo
	 * @return -1代表更新失败，1代表更新成功，0代表原始密码错误
	 */
	public int updateUserInfo(UpdateUserInfo userInfo) {
		init();
		String sql = "select upass from user where uname = ?";
		String pass = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userInfo.getUsername());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pass = rs.getString("upass");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pass.equals(userInfo.getOrigpass())) {

			sql = "update user set upass = ? ,uemail = ? where uname = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userInfo.getNewpass());
				pstmt.setString(2, userInfo.getNewemail());
				pstmt.setString(3, userInfo.getUsername());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return 0;
		}
		if (result == 1) {
			return result;
		} else {
			return -1;
		}
	}

	/**
	 * 返回user的身份
	 * 
	 * @param user
	 * @return
	 */
	public int gettype(User user) {
		init();
		String sql = "select utype from user where uname = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUname());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("utype");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 更新登陆状态
	 * 
	 * @param user
	 * @return
	 */
	public boolean update(User user) {
		init();
		String sql = "update user set isLogin = 0 where uname = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUname());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 完成注册操作，添加数据进数据库
	 * 
	 * @param user
	 * @return
	 */
	public boolean insert(User user) {
		init();
		String sql = "insert into user(uname,upass,uemail,utype,isLogin,regtime,credit_standing) values (?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUname());
			pstmt.setString(2, user.getUpass());
			pstmt.setString(3, user.getUemail());
			pstmt.setInt(4, user.getUtype());
			pstmt.setInt(5, user.getIsLogin());
			pstmt.setString(6, user.getDatetime());
			pstmt.setInt(7, user.getCredit_standing());
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	/**
	 * 判断用户名是否存在
	 * 
	 * @param user
	 * @return
	 */
	public boolean userExists(User user) {
		init();
		String sql = "select * from user where uname = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUname());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	public boolean checkUser(User user) {
		String pass = null;
		init();
		String sql = "select upass from user where uname = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUname());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pass = rs.getString("upass");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		if (user.getUpass().equals(pass)) {
			return true;
		}
		return false;
	}

	public void close() {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

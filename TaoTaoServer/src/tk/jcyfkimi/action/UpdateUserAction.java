package tk.jcyfkimi.action;

import java.io.DataInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import tk.jcyfkimi.DAO.UserDAO;
import tk.jcyfkimi.beans.UpdateUserInfo;
import tk.jcyfkimi.beans.User;
import tk.jcyfkimi.utils.GsonUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateUserAction extends ActionSupport implements
		ServletRequestAware {
	private ServletInputStream sis = null;
	private DataInputStream din = null;

	private HttpServletRequest request;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String jsonstr = getGson();
		System.out.println(jsonstr);
		// 解析json数据
		GsonUtils gsonUtils = new GsonUtils();
		UpdateUserInfo userInfo = gsonUtils.userInfoParse(jsonstr);
		UserDAO userDAO = new UserDAO();
		int result = userDAO.updateUserInfo(userInfo);
		if(result == 1){
			message = "success" ;
		} else if(result == 0){
			message = "error";
		} else if(result == -1){
			message = "failed";
		}
		return Action.SUCCESS;
	}

	public String getGson() {
		StringBuffer sb = new StringBuffer();

		try {
			sis = request.getInputStream();
			din = new DataInputStream(sis);
			int ch;
			while ((ch = din.read()) != -1) {
				sb.append((char) ch);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 关闭着一块，会导致出错，有待解决
		return sb.toString();
	}

	public void close() {
		try {
			din.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

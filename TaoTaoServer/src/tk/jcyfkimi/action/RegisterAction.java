package tk.jcyfkimi.action;

import java.io.DataInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import tk.jcyfkimi.DAO.UserDAO;
import tk.jcyfkimi.beans.User;
import tk.jcyfkimi.utils.GsonUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport implements
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
		// ����json����
		GsonUtils gsonUtils = new GsonUtils();
		User user = gsonUtils.userParse(jsonstr);
		UserDAO userDAO = new UserDAO();
		if (!userDAO.userExists(user)) {
			if (userDAO.insert(user)) {
				message = "success";
			} else {
				message = "error";
			}
		} else {
			message = "�û����Ѵ���";
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
		} // �ر���һ�飬�ᵼ�³������д����
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
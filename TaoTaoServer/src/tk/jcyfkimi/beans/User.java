package tk.jcyfkimi.beans;

import java.util.Date;

public class User {

	private String uname;
	private String upass;
	private String uemail;
	private int utype;
	private int isLogin;
	private int credit_standing;
	private String datetime;
	public User(String uname, String upass, String uemail, int utype,
			int isLogin, int creditStanding, String datetime) {
		super();
		this.uname = uname;
		this.upass = upass;
		this.uemail = uemail;
		this.utype = utype;
		this.isLogin = isLogin;
		credit_standing = creditStanding;
		this.datetime = datetime;
	}
	public User() {
		super();
	}
	public User(String uname, String upass) {
		super();
		this.uname = uname;
		this.upass = upass;
	}
	
	public int getCredit_standing() {
		return credit_standing;
	}
	public String getDatetime() {
		return datetime;
	}
	public int getIsLogin() {
		return isLogin;
	}
	public String getUemail() {
		return uemail;
	}
	public String getUname() {
		return uname;
	}
	public String getUpass() {
		return upass;
	}
	public int getUtype() {
		return utype;
	}
	public void setCredit_standing(int creditStanding) {
		credit_standing = creditStanding;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public void setIsLogin(int isLogin) {
		this.isLogin = isLogin;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public void setUtype(int utype) {
		this.utype = utype;
	}
	
}
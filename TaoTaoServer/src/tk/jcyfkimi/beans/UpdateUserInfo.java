package tk.jcyfkimi.beans;

public class UpdateUserInfo {

	private String username;
	private String origpass ;
	private String newpass;
	private String newemail;
	public String getOrigpass() {
		return origpass;
	}
	public UpdateUserInfo() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public UpdateUserInfo(String username, String origpass, String newpass,
			String newemail) {
		super();
		this.username = username;
		this.origpass = origpass;
		this.newpass = newpass;
		this.newemail = newemail;
	}
	public void setOrigpass(String origpass) {
		this.origpass = origpass;
	}
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	public String getNewemail() {
		return newemail;
	}
	public void setNewemail(String newemail) {
		this.newemail = newemail;
	}
	
	
}

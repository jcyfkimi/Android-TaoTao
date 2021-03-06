package tk.jcyfkimi.util;

import tk.jcyfkimi.beans.UpdateUserInfo;
import tk.jcyfkimi.beans.User;

import com.google.gson.Gson;

public class GsonUtils {

	/**
	 * 返回一个json格式的user字符串
	 * @param user
	 * @return
	 */
	public String getJson(User user){
		Gson gson = new Gson();
		//System.out.println(gson.toJson(user));
		return gson.toJson(user);
	}
	
	
	
	public String getJsonFromUpdateUserInfo(UpdateUserInfo userInfo){
		Gson gson = new Gson();
		return gson.toJson(userInfo);
	}
}

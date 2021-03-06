package tk.jcyfkimi.utils;


import tk.jcyfkimi.beans.UpdateUserInfo;
import tk.jcyfkimi.beans.User;

import com.google.gson.Gson;

public class GsonUtils {

	public User userParse(String jsonstr){
		Gson gson = new Gson();
		User user = gson.fromJson(jsonstr, User.class);
		return user;
	}
	
	public UpdateUserInfo userInfoParse(String jsonstr){
		Gson gson = new Gson();
		UpdateUserInfo userInfo = gson.fromJson(jsonstr, UpdateUserInfo.class);
		return userInfo;
	}
}

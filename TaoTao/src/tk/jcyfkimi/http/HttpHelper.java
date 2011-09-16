package tk.jcyfkimi.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import tk.jcyfkimi.beans.Goods;

/**
 * 实现各类和服务器交互的功能
 * 
 * @author Administrator
 * 
 */
public class HttpHelper {

	private StringBuffer sb = new StringBuffer();

	private HttpClient httpClient;
	private HttpPost httpRequest;
	private HttpResponse response;

	/**
	 * 接受从服务器返回来的list，并且返回到UI界面中去
	 * @return
	 */
	public List<Goods> getGoodsList() {
		List<Goods> list = new ArrayList<Goods>();
		String url = "http://10.0.2.2:8080/TaoTaoServer/goods";
		JSONObject jsonObj = sendToServer(url);
		System.out.println(jsonObj);
		try {
			JSONArray jsonArray = jsonObj.getJSONArray("list");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject json = jsonArray.getJSONObject(i);
				Goods goods = new Goods(json.getInt("gid"), json
						.getString("gname"), json.getInt("gstorage"), json
						.getDouble("gprice"), json.getInt("guid"));
				list.add(goods);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 更新用户信息，返回int值，-1失败，0原始密码错误，1注册成功
	 * @param jsonstr
	 * @return
	 */
	public int updateUserInfo(String jsonstr){
		String message = null;
		String url = "http://10.0.2.2:8080/TaoTaoServer/updateUser";
		JSONObject jsonObj = sendToServer(url, jsonstr);
		try {
			message = jsonObj.getString("message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(message.equals("success")){
			return 1;
		} else if(message.equals("error")){
			return 0;
		}
		return -1;
	}
	/**
	 * 注册账号 返回一个int值，-1代表已存在用户名，0代表注册失败，1代表注册成功
	 * 
	 * @param jsonstr
	 * @return
	 */
	public int register(String jsonstr) {
		String message = null;
		String url = "http://10.0.2.2:8080/TaoTaoServer/register";
		JSONObject jsonObj = sendToServer(url, jsonstr);
		// System.out.println(sb.toString());
		try {
			message = jsonObj.getString("message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (message.equals("success")) {
			// System.out.println(message);
			return 1;
		} else if (message.equals("error")) {
			return 0;
		}

		return -1;
	}

	/**
	 * 检查登陆，发送一个json的字符串到服务器 返回utype到客户端
	 * 
	 * @param jsonstr
	 * @return
	 */
	public int checkLogin(String jsonstr) {
		String message = null;
		int utype = 1;
		String url = "http://10.0.2.2:8080/TaoTaoServer/login";
		System.out.println(url);
		JSONObject jsonObj = sendToServer(url, jsonstr);
		System.out.println(sb.toString());
		try {
			message = jsonObj.getString("message");
			utype = jsonObj.getInt("utype");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (message.equals("success") && utype != -1) {
			return utype;
		}

		return -1;
	}

	/**
	 * 发送json数据到服务器 接收从服务器返回的json数据
	 * 
	 * @param url
	 * @param jsonstr
	 * @return
	 */
	private JSONObject sendToServer(String url, String jsonstr) {
		JSONObject jsonObject = null;
		httpRequest = new HttpPost(url);
		try {
			StringEntity se = new StringEntity(jsonstr);
			httpRequest.setEntity(se);
			response = new DefaultHttpClient().execute(httpRequest);
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader bufferedReader2 = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));
				for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2
						.readLine()) {
					sb.append(s);
				}
			}
			jsonObject = new JSONObject(sb.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 不发送任何数据，直接接受从服务器返回的数据
	 * 
	 * @param url
	 * @return
	 */
	private JSONObject sendToServer(String url) {
		JSONObject jsonObject = null;
		httpRequest = new HttpPost(url);
		try {
			response = new DefaultHttpClient().execute(httpRequest);
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader bufferedReader2 = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));
				for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2
						.readLine()) {
					sb.append(s);
				}
			}
//			System.out.println(sb);
			jsonObject = new JSONObject(sb.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}

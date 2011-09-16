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
 * ʵ�ָ���ͷ����������Ĺ���
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
	 * ���ܴӷ�������������list�����ҷ��ص�UI������ȥ
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
	 * �����û���Ϣ������intֵ��-1ʧ�ܣ�0ԭʼ�������1ע��ɹ�
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
	 * ע���˺� ����һ��intֵ��-1�����Ѵ����û�����0����ע��ʧ�ܣ�1����ע��ɹ�
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
	 * ����½������һ��json���ַ����������� ����utype���ͻ���
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
	 * ����json���ݵ������� ���մӷ��������ص�json����
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
	 * �������κ����ݣ�ֱ�ӽ��ܴӷ��������ص�����
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
package tk.jcyfkimi.UI;

import tk.jcyfkimi.R;
import tk.jcyfkimi.UI.buyer.GoodsListActivity;
import tk.jcyfkimi.UI.saler.OnSaleActivity;
import tk.jcyfkimi.beans.User;
import tk.jcyfkimi.http.HttpHelper;
import tk.jcyfkimi.util.GsonUtils;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 登录Activity，用于显示登录界面，并且进行验证
 * 通过验证则进入相应身份的界面（买家，卖家）
 * 登录失败则跳入注册界面。
 * 
 */
public class LoginActivity extends Activity {
    /** Called when the activity is first created. */
	private TextView forget = null;
	private EditText editName = null;
	private EditText editPass = null;
	private Button btnLogin = null;
	private Button btnReg = null;
	
	private void findViews(){
		forget = (TextView) findViewById(R.id.forget);
		editName = (EditText) findViewById(R.id.uname);
		editPass = (EditText) findViewById(R.id.upass);
		btnLogin = (Button) findViewById(R.id.btnlogin);
		btnReg = (Button) findViewById(R.id.btnreg);
	}
	
	private void setListener(){
		forget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//取得用户名，服务器向用户名的邮箱发送邮件，找回密码
				System.out.println("TextView onClick");
			}
		});
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//访问web进行用户名密码的认证，认证通过跳转。
				//ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"请稍后","正在登录中...",true,false);
				User user = new User(editName.getText().toString(), editPass.getText().toString());
				GsonUtils gutils = new GsonUtils();
				HttpHelper httpHelper = new HttpHelper();
				if(httpHelper.checkLogin(gutils.getJson(user)) == 1){
					//成功登陆
//					Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
					Intent buyerIntent = new Intent();
					buyerIntent.putExtra("uname", editName.getText().toString());
					buyerIntent.setClass(LoginActivity.this, GoodsListActivity.class);
					LoginActivity.this.startActivity(buyerIntent);
					//progressDialog.dismiss();
				} else if (httpHelper.checkLogin(gutils.getJson(user)) == 2){
					//登陆到卖家页面
					Intent salerIntent = new Intent();
					salerIntent.setClass(LoginActivity.this, OnSaleActivity.class);
					LoginActivity.this.startActivity(salerIntent);
				} else {
					//角色出错,登陆失败
				}
			}
		});
		btnReg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, RegisterActivity.class);
				LoginActivity.this.startActivity(intent);
			}
		});
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViews();
        setListener();
    }
}
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
 * ��¼Activity��������ʾ��¼���棬���ҽ�����֤
 * ͨ����֤�������Ӧ���ݵĽ��棨��ң����ң�
 * ��¼ʧ��������ע����档
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
				//ȡ���û��������������û��������䷢���ʼ����һ�����
				System.out.println("TextView onClick");
			}
		});
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����web�����û����������֤����֤ͨ����ת��
				//ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"���Ժ�","���ڵ�¼��...",true,false);
				User user = new User(editName.getText().toString(), editPass.getText().toString());
				GsonUtils gutils = new GsonUtils();
				HttpHelper httpHelper = new HttpHelper();
				if(httpHelper.checkLogin(gutils.getJson(user)) == 1){
					//�ɹ���½
//					Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
					Intent buyerIntent = new Intent();
					buyerIntent.putExtra("uname", editName.getText().toString());
					buyerIntent.setClass(LoginActivity.this, GoodsListActivity.class);
					LoginActivity.this.startActivity(buyerIntent);
					//progressDialog.dismiss();
				} else if (httpHelper.checkLogin(gutils.getJson(user)) == 2){
					//��½������ҳ��
					Intent salerIntent = new Intent();
					salerIntent.setClass(LoginActivity.this, OnSaleActivity.class);
					LoginActivity.this.startActivity(salerIntent);
				} else {
					//��ɫ����,��½ʧ��
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
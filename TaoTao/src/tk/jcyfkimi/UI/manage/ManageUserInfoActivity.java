package tk.jcyfkimi.UI.manage;

import tk.jcyfkimi.R;
import tk.jcyfkimi.beans.UpdateUserInfo;
import tk.jcyfkimi.http.HttpHelper;
import tk.jcyfkimi.util.EmailRegex;
import tk.jcyfkimi.util.GsonUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageUserInfoActivity extends Activity {

	private EditText origpass = null;
	private EditText newpass = null;
	private EditText newpasscfm = null;
	private EditText newemail = null;
	private Button btnupdate = null;
	private Button btnreset = null;
	private String username = null;

	private void findViews() {
		origpass = (EditText) findViewById(R.id.origpass);
		newpass = (EditText) findViewById(R.id.newpass);
		newpasscfm = (EditText) findViewById(R.id.newpasscfm);
		newemail = (EditText) findViewById(R.id.newemail);
		btnupdate = (Button) findViewById(R.id.btnuserinfoupdate);
		btnreset = (Button) findViewById(R.id.btnreset);
	}

	private void setListeners() {
		btnupdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (validate()) {
					UpdateUserInfo userInfo = new UpdateUserInfo(username,origpass
							.getText().toString(),
							newpass.getText().toString(), newemail.getText()
									.toString());
					GsonUtils gsonUtils = new GsonUtils();
					HttpHelper httpHelper = new HttpHelper();
					System.out.println(gsonUtils.getJsonFromUpdateUserInfo(userInfo));
					int result = httpHelper.updateUserInfo(gsonUtils.getJsonFromUpdateUserInfo(userInfo));
					if(result == 1){
						showWithToast("更新成功");
					} else if(result == 0){
						showWithToast("原始密码错误");
					} else if(result == -1){
						showWithToast("更新失败");
					}
				}
			}
		});

		btnreset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				origpass.setText("");
				newpass.setText("");
				newpasscfm.setText("");
				newemail.setText("");
			}
		});
	}

	private boolean validate() {
		// 以下为对空值的判断
		if (origpass.getText().length() == 0) {
			showWithToast("用户名不能为空");
			return false;
		} else if (newpass.getText().length() == 0) {
			showWithToast("密码不能为空");
			return false;
		} else if (newpasscfm.getText().length() == 0) {
			showWithToast("确认密码不能为空");
			return false;
		} else if (newemail.getText().length() == 0) {
			showWithToast("email不能为空");
			return false;
		}
		// 对密码和邮箱进行验证
		if (!newpass.getText().toString().equals(
				newpasscfm.getText().toString())) {
			showWithToast("两次输入的密码不一致");
			return false;
		} else if (!EmailRegex.mailRegex(newemail.getText().toString())) {
			showWithToast("邮箱非法");
			return false;
		}
		return true;
	}

	public void showWithToast(String str) {
		Toast.makeText(ManageUserInfoActivity.this, str, Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manageuserinfo);
		findViews();
		setListeners();
		Intent intent = getIntent();
		username = intent.getStringExtra("uname");
	}

}

package tk.jcyfkimi.UI;

import java.text.SimpleDateFormat;
import java.util.Date;

import tk.jcyfkimi.R;
import tk.jcyfkimi.beans.User;
import tk.jcyfkimi.http.HttpHelper;
import tk.jcyfkimi.util.EmailRegex;
import tk.jcyfkimi.util.GsonUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 
 * 注册界面，用户名，密码，邮箱，买家卖家 注册成功后自动跳转到相应的界面 注册时，传送json格式数据到服务器端进行解析，然后插入数据。
 * 
 */
public class RegisterActivity extends Activity {

	private int utype = 1;
	private EditText editName = null;
	private EditText editPass = null;
	private EditText editPasscfm = null;
	private EditText editEmail = null;
	private RadioGroup rdoGroup = null;
	private RadioButton rdobuy = null;
	private RadioButton rdosale = null;
	private Button regBtn = null;
	private Button regReset = null;

	private void findViews() {
		editName = (EditText) findViewById(R.id.regname);
		editPass = (EditText) findViewById(R.id.regpass);
		editPasscfm = (EditText) findViewById(R.id.regpasscfm);
		editEmail = (EditText) findViewById(R.id.regemail);
		rdoGroup = (RadioGroup) findViewById(R.id.rdogtype);
		rdobuy = (RadioButton) findViewById(R.id.ubtype);
		rdosale = (RadioButton) findViewById(R.id.ustype);
		regBtn = (Button) findViewById(R.id.btnreg);
		regReset = (Button) findViewById(R.id.btnreset);
	}

	private void setListener() {
		regBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 空值判断
				if (validate()) {
					// 与服务器进行交互，插入数据
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					// System.out.println(sdf.format(new Date()));
					User user = new User(editName.getText().toString(),
							editPass.getText().toString(), editEmail.getText()
									.toString(), utype, 1, 0, sdf
									.format(new Date()));
					GsonUtils gUtils = new GsonUtils();
					HttpHelper httpHelper = new HttpHelper();
					if (httpHelper.register(gUtils.getJson(user)) == 1) {
						//注册成功自动跳转到相应的界面去
						showWithToast("注册成功");
					} else if(httpHelper.register(gUtils.getJson(user)) == 0){
						showWithToast("注册失败");
					} else {
						showWithToast("用户名已存在");
					}
				}
			}
		});

		rdoGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == R.id.ustype) {
					utype = 2;
				}
			}
		});
		regReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clear();
			}
		});
	}

	private boolean validate() {
		// 以下为对空值的判断
		if (editName.getText().length() == 0) {
			showWithToast("用户名不能为空");
			return false;
		} else if (editPass.getText().length() == 0) {
			showWithToast("密码不能为空");
			return false;

		} else if (editPasscfm.getText().length() == 0) {
			showWithToast("确认密码不能为空");
			return false;
		} else if (editEmail.getText().length() == 0) {
			showWithToast("email不能为空");
			return false;
		}
		// 对密码和邮箱进行验证
		if (!editPass.getText().toString().equals(
				editPasscfm.getText().toString())) {
			showWithToast("两次输入的密码不一致");
			return false;
		} else if (!EmailRegex.mailRegex(editEmail.getText().toString())) {
			showWithToast("邮箱非法");
			return false;
		}
		return true;
	}

	public void showWithToast(String str) {
		Toast.makeText(RegisterActivity.this, str, Toast.LENGTH_SHORT).show();
	}

	private void clear(){
		editName.setText("");
		editPass.setText("");
		editPasscfm.setText("");
		editEmail.setText("");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reg);
		findViews();
		setListener();
	}

}

package tk.jcyfkimi.UI.saler;

import tk.jcyfkimi.R;
import tk.jcyfkimi.UI.buyer.GoodsListActivity;
import tk.jcyfkimi.UI.manage.ManageUserInfoActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class OnSaleActivity extends Activity {
	
	private static final int EXIT = Menu.FIRST;
	private static final int MANAGE = Menu.FIRST+1;
	private static final int ADD = Menu.FIRST+2;
	private static final int SOLD = Menu.FIRST+3;
	private static final int MANAGEGOODS = Menu.FIRST+4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onsale);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, EXIT, 1, R.string.menuexit);
		menu.add(1, MANAGE, 1, R.string.menumanage);
		menu.add(2,ADD,1,R.string.addgoods);
		menu.add(2,SOLD,1,R.string.soldgoods);
		menu.add(2,MANAGEGOODS,1,R.string.managegoods);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case EXIT:
			finish();
			break;
		case MANAGE:
			Intent manageUserInfoIntent = new Intent();
			manageUserInfoIntent.setClass(OnSaleActivity.this, ManageUserInfoActivity.class);			
			OnSaleActivity.this.startActivity(manageUserInfoIntent);			
			break;
		case ADD:
			break;
		case SOLD:
			break;
		case MANAGEGOODS:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}

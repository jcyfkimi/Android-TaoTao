package tk.jcyfkimi.UI.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tk.jcyfkimi.R;
import tk.jcyfkimi.UI.manage.ManageUserInfoActivity;
import tk.jcyfkimi.beans.Goods;
import tk.jcyfkimi.http.HttpHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class GoodsListActivity extends Activity {

	private static final int GROUP = 1;
	private static final int EXIT = Menu.FIRST;
	private static final int MANAGE = Menu.FIRST+1;
	
	private Spinner order = null;
	private TextView userinfo = null;
	private ListView goodsList = null;
	private String username = null;

	private void findViews() {
		userinfo = (TextView) findViewById(R.id.userinfo);
		order = (Spinner) findViewById(R.id.switchorder);
		goodsList = (ListView) findViewById(R.id.goodlistview);
	}

	// 设置Spinner
	private void setSpinner() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				GoodsListActivity.this, R.array.switchorder,
				android.R.layout.simple_spinner_item);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		order.setAdapter(adapter);
		order.setPrompt("请选择");
	}

/*	private void setListView() {
		SimpleAdapter simpleAdapter = new SimpleAdapter(GoodsListActivity.this,
				getDate(), R.layout.blist, new String[] { "blistImage",
						"blistname", "blistprice", "blistbuy" }, new int[] {
						R.id.blistImage, R.id.blistname, R.id.blistprice,
						R.id.blistbuy });
		goodsList.setAdapter(simpleAdapter);
	}
*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buyergoodlist);
		findViews();
		setSpinner();
//		setListView();
/*		SimpleAdapter simpleAdapter = new SimpleAdapter(GoodsListActivity.this,
				getDate(), R.layout.blist, new String[] { "blistImage",
						"blistname", "blistprice", "blistbuy" }, new int[] {
						R.id.blistImage, R.id.blistname, R.id.blistprice,
						R.id.blistbuy });*/
		HttpHelper httpHelper = new HttpHelper();
		List<Goods> glist = httpHelper.getGoodsList();
		Intent intent = getIntent();
		username = intent.getStringExtra("uname");
		userinfo.setText(username);
	}

	public List<Map<String,Object>> getDate(List<Goods> glist){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for (Goods goods : glist) {
			Map<String ,Object> map = new HashMap<String,Object>();
			map.put("gid", goods.getGid());
			map.put("gname", goods.getGname());
			map.put("gstorage", goods.getGstorage());
			map.put("gprice", goods.getGprice());
			map.put("guid", goods.getGuid());
			list.add(map);
		}
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(GROUP, EXIT, 1, R.string.menuexit);
		menu.add(GROUP, MANAGE, 1, R.string.menumanage);
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
			manageUserInfoIntent.putExtra("uname", username);
			manageUserInfoIntent.setClass(GoodsListActivity.this, ManageUserInfoActivity.class);
			//这里应该是startactivityforresult，修改完数据后返回这个界面
			GoodsListActivity.this.startActivity(manageUserInfoIntent);			
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	
}

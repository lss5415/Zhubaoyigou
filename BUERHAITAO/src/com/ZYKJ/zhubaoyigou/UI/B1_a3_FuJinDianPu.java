package com.ZYKJ.zhubaoyigou.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ZYKJ.zhubaoyigou.R;
import com.ZYKJ.zhubaoyigou.adapter.B1_a3_MeiRiHaoDian1Adapter;
import com.ZYKJ.zhubaoyigou.base.BaseActivity;
import com.ZYKJ.zhubaoyigou.utils.HttpUtils;
import com.ZYKJ.zhubaoyigou.utils.Tools;
import com.ZYKJ.zhubaoyigou.view.MyListView;
import com.ZYKJ.zhubaoyigou.view.RequestDailog;
import com.alibaba.fastjson.JSONException;
import com.external.maxwin.view.XListView.IXListViewListener;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * @author lss 2015年6月17日 附近店铺
 *
 */
public class B1_a3_FuJinDianPu extends BaseActivity implements IXListViewListener  {
	//返回
	private ImageButton b1_a3_goodstoreback;
	private MyListView listview_b1_a3_goodstore;
	private List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	private B1_a3_MeiRiHaoDian1Adapter goodstoredapter;
	int curpage=1;
	private Handler mHandler = new Handler();//异步加载或刷新
	private TextView title_head;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(R.layout.ui_b1_a3_meirihaodian);
		
		title_head = (TextView)findViewById(R.id.title_head);
		title_head.setText("附近店铺");
		b1_a3_goodstoreback = (ImageButton)findViewById(R.id.b1_a3_goodstoreback);
		listview_b1_a3_goodstore = (MyListView)findViewById(R.id.listview_b1_a3_goodstore);
		goodstoredapter = new B1_a3_MeiRiHaoDian1Adapter(B1_a3_FuJinDianPu.this,data);
		listview_b1_a3_goodstore.setAdapter(goodstoredapter);
		listview_b1_a3_goodstore.setPullLoadEnable(true);
		listview_b1_a3_goodstore.setPullRefreshEnable(true);
		listview_b1_a3_goodstore.setXListViewListener(this, 0);
		listview_b1_a3_goodstore.setRefreshTime();
		RequestDailog.showDialog(this, "正在加载数据，请稍后");
//		HttpUtils.getNearStore(res_goodstore, "5","0","88","80", "100");
		HttpUtils.getNearStore(res_goodstore, "5",String.valueOf(curpage),getSharedPreferenceValue("cityid"),getSharedPreferenceValue("lng"),getSharedPreferenceValue("lat"));
		listview_b1_a3_goodstore.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				String storeid = data.get(arg2-1).get("store_id");
				intent.putExtra("store_id", storeid);
				intent.setClass(B1_a3_FuJinDianPu.this,BX_DianPuXiangQingActivity.class);
				startActivity(intent);
			}
		});
		setListener(b1_a3_goodstoreback);
	}
	
	/**
	 * 每日好店
	 */
	JsonHttpResponseHandler res_goodstore = new JsonHttpResponseHandler()
	{

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			RequestDailog.closeDialog();
			
			String error=null;
			JSONObject datas=null;
			try {
				 datas = response.getJSONObject("datas");
				 error = response.getString("error");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (error==null)//成功
			{
				try {
					org.json.JSONArray array = datas.getJSONArray("store_list");
					Tools.Log("res_Points_array="+array);
					if (curpage>1) {
						
					}else {
						data.clear();
					}
					for (int i = 0; i < array.length(); i++) {
						JSONObject jsonItem = array.getJSONObject(i);
						Map<String, String> map = new HashMap(); 
						map.put("store_name", jsonItem.getString("store_name"));
						map.put("store_evaluate_count", jsonItem.getString("store_evaluate_count"));
						map.put("sc_name", jsonItem.getString("sc_name"));
						map.put("area_info", jsonItem.getString("area_info"));
						map.put("store_address", jsonItem.getString("store_address"));
						map.put("store_label", jsonItem.getString("store_label"));
						map.put("store_desccredit", jsonItem.getString("store_desccredit"));
						map.put("juli", jsonItem.getString("juli"));
						map.put("store_id", jsonItem.getString("store_id"));
						data.add(map);
					}
					goodstoredapter.notifyDataSetChanged();
				} 
				catch (org.json.JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else//失败 
			{
				Tools.Log("res_Points_error="+error+"");
			}
		}
	};
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.b1_a3_goodstoreback:
			B1_a3_FuJinDianPu.this.finish();
			break;
			
		}
	}
	@Override
	public void onRefresh(int id) {
		/**下拉刷新 重建*/
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
					curpage = 1;
					HttpUtils.getNearStore(res_goodstore, "5",String.valueOf(curpage),getSharedPreferenceValue("cityid"),getSharedPreferenceValue("lng"),getSharedPreferenceValue("lat"));
					onLoad();
			}
		}, 1000);

	}
	@Override
	public void onLoadMore(int id) {
		/**上拉加载分页*/
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
					curpage += 1;
					HttpUtils.getNearStore(res_goodstore, "5",String.valueOf(curpage),getSharedPreferenceValue("cityid"),getSharedPreferenceValue("lng"),getSharedPreferenceValue("lat"));
					onLoad();
			}
		}, 1000);		
	}

	private void onLoad() {
		listview_b1_a3_goodstore.stopRefresh();
		listview_b1_a3_goodstore.stopLoadMore();
		listview_b1_a3_goodstore.setRefreshTime();
	}
}
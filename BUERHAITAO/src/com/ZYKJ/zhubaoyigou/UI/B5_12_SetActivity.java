package com.ZYKJ.zhubaoyigou.UI;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.ZYKJ.zhubaoyigou.R;
import com.ZYKJ.zhubaoyigou.base.BaseActivity;
import com.ZYKJ.zhubaoyigou.utils.HttpUtils;
import com.ZYKJ.zhubaoyigou.utils.Tools;
import com.ZYKJ.zhubaoyigou.view.RequestDailog;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * 设置页面
 * @author zyk
 *
 */
public class B5_12_SetActivity extends BaseActivity {

	private RelativeLayout cetification,resetpasswd,aboutus,appupdate;
	private Button btn_logout;
	private ImageButton set_back;
	String mobile=null;
	String key=null;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mobile = getSharedPreferenceValue("mobile");
		key = getSharedPreferenceValue("key");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_set);
		initView();
		setListener(cetification,resetpasswd,aboutus,appupdate,btn_logout,set_back);
	}
	private void initView() {
		// TODO Auto-generated method stub
		cetification=(RelativeLayout) findViewById(R.id.set_1);//实名认证
		resetpasswd=(RelativeLayout) findViewById(R.id.RelativeLayout01);//重置密码
		aboutus=(RelativeLayout) findViewById(R.id.RelativeLayout02);//关于我们
		appupdate=(RelativeLayout) findViewById(R.id.RelativeLayout04);//版本跟新
		btn_logout=(Button) findViewById(R.id.btn_logout);//退出登录
		set_back=(ImageButton) findViewById(R.id.set_back);
	}
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.set_1://实名认证
			Intent intent_cet=new Intent();
			intent_cet.setClass(this, B5_12_1_Certification.class);
			startActivity(intent_cet);
			break;
		case R.id.RelativeLayout01://重置密码
			Intent intent_reset=new Intent();
			intent_reset.setClass(this, B5_1_1_RegistActivity.class);
			intent_reset.putExtra("FIND_OR_REGIST", 2);
			startActivity(intent_reset);
			break;
		case R.id.RelativeLayout02://关于我们
			Tools.Notic(this, "南京达尔蒙特网络科技有限公司在中国金陵古都南京成立，总部设立于鼓楼区江东软件城。是一家专注于互联网销售、推广、培训为一体的专业机构。旗下享拥“珠宝易购”和“饰美易购”两大电商交易平台，珠宝易购“是一个以珠宝黄金翡翠玉器水晶玛瑙等各种高中低档宝石半宝石为专业的珠宝电商交易平台。“饰美易购  ，是一个经营中低档珠宝和高中低档饰品工艺品的电商交易平台为全民创造最齐全、最丰富、最具性价比的珠宝及饰品。", null);
			break;
		case R.id.RelativeLayout04://版本跟新
//			Toast.makeText(this, "目前为最新版本", Toast.LENGTH_LONG).show();
			Tools.Notic(this,"目前为最新版本", null);
			break;
		case R.id.btn_logout://退出登录
			RequestDailog.showDialog(this, "正在退出登录，请稍后");
			HttpUtils.logout(res_logout,mobile, key);
			break;
		case R.id.set_back://退出设置页面
			this.finish();
			break;

		default:
			break;
		}
	}
	JsonHttpResponseHandler res_logout = new JsonHttpResponseHandler()
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
				 error = datas.getString("error");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (error==null) //注销成功
			{
				putSharedPreferenceValue("userid", "");
				putSharedPreferenceValue("username", "");
				putSharedPreferenceValue("mobile", "");
				putSharedPreferenceValue("key", "");
				Tools.Notic(B5_12_SetActivity.this, "注销成功", new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent  intent_tomainavtivity = new Intent(B5_12_SetActivity.this, B0_MainActivity.class);
						startActivity(intent_tomainavtivity);
					}
				});
			}
			else //注销失败
			{
				if (error.equals("请登录")) {
					Tools.Notic(B5_12_SetActivity.this, "请叫我请登录", null);
					putSharedPreferenceValue("userid", "");
					putSharedPreferenceValue("username", "");
					putSharedPreferenceValue("mobile", "");
					putSharedPreferenceValue("key", "");
					Tools.Notic(B5_12_SetActivity.this, "注销成功", new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent  intent_tomainavtivity = new Intent(B5_12_SetActivity.this, B0_MainActivity.class);
							startActivity(intent_tomainavtivity);
						}
					});
				}else {
					Tools.Notic(B5_12_SetActivity.this, error+"", null);
				}
			}
			
		}
		@Override
		public void onFailure(int statusCode, Header[] headers,
				Throwable throwable, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(statusCode, headers, throwable, errorResponse);
			Tools.Log("logout-errorResponse="+errorResponse);
		}

	
		
		
		
	};
	
	
}

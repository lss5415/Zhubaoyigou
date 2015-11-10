package com.zykj.zhubaoyigou_seller;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.zykj.zhubaoyigou_seller.R;
import com.zykj.zhubaoyigou_seller.base.BaseActivity;
import com.zykj.zhubaoyigou_seller.utils.HttpUtils;
import com.zykj.zhubaoyigou_seller.utils.ToastView;
import com.zykj.zhubaoyigou_seller.utils.Tools;
/**
 * 设置页面
 * @author zyk
 *
 */
public class B5_SetActivity extends BaseActivity{

	RelativeLayout certification,aboutus,aboutapp,updateapp;
	Button btn_logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView(R.layout.ui_set);
		initUI();
		setListener(certification,aboutus,aboutapp,updateapp,btn_logout);
		
	}
	private void initUI() {
		// TODO Auto-generated method stub
		certification = (RelativeLayout) findViewById(R.id.certification);
		aboutus = (RelativeLayout) findViewById(R.id.aboutus);
		aboutapp = (RelativeLayout) findViewById(R.id.aboutapp);
		updateapp = (RelativeLayout) findViewById(R.id.updateapp);
		btn_logout = (Button) findViewById(R.id.btn_logout);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.certification://认证
			//store_state 店铺状态 3:待认证 2:待审核,1:正常,0:已关闭
			if (getSharedPreferenceValue("store_state")=="2") 
			{
				Tools.Notic(this, "待审核，请稍后", null);
			}else {
				Intent intent_to_cetification = new Intent(this,B5_2_certification.class);
				startActivity(intent_to_cetification);
			}
			break;
		case R.id.aboutus://关于我们
			Tools.Notic(this, "南京达尔蒙特网络科技有限公司在中国金陵古都南京成立，总部设立于鼓楼区江东软件城。是一家专注于互联网销售、推广、培训为一体的专业机构。旗下享拥“珠宝易购”和“饰美易购”两大电商交易平台，珠宝易购“是一个以珠宝黄金翡翠玉器水晶玛瑙等各种高中低档宝石半宝石为专业的珠宝电商交易平台。“饰美易购  ，是一个经营中低档珠宝和高中低档饰品工艺品的电商交易平台为全民创造最齐全、最丰富、最具性价比的珠宝及饰品。", null);
			break;
		case R.id.aboutapp://关于app
			Tools.Notic(this, "公司本着以诚信第一，服务至上为原则的服务理念，努力打造中国最专业、诚信、齐全的珠宝电商交易平台，我们传递的是信任和价值，让消费者实现无忧购物，努力打造互联网珠宝电商行业的成功典范.", null);
			break;
		case R.id.updateapp://应用更新
			Tools.Notic(this, "目前已经是最新版本，请留意各大应用市场，及时更新", null);
			break;
		case R.id.btn_logout://登出
			HttpUtils.logout(res_logout, getSharedPreferenceValue("mobile"), getSharedPreferenceValue("key"));
			break;

		default:
			break;
		}
		super.onClick(v);
	}
	JsonHttpResponseHandler res_logout = new JsonHttpResponseHandler()
	{

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			Tools.Log("退出登录="+response);
			JSONObject datas = null;
			String error = null;
			try {
				datas = response.getJSONObject("datas");
				error = datas.getString("error");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (error == null) {
				Tools.Notic(B5_SetActivity.this, "退出登录成功", new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						putSharedPreferenceValue("isLogin", "false");
						Intent intent = new Intent(B5_SetActivity.this,B0_MainActivity.class);
						startActivity(intent);
					}
				});
				
			}else {
				Tools.Notic(B5_SetActivity.this, error, null);
			}
			
		}
		
		
	};
				// 退出操作
				private boolean isExit = false;
				public boolean onKeyDown(int keyCode, KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						if (isExit == false) {
							isExit = true;
							ToastView toast = new ToastView(getApplicationContext(),
									"再按一次退出程序");
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
							Handler mHandler = new Handler() {
								@Override
								public void handleMessage(Message msg) {
									super.handleMessage(msg);
									isExit = false;
								}
							};
							mHandler.sendEmptyMessageDelayed(0, 3000);
							return true;
						} else {
							android.os.Process.killProcess(android.os.Process.myPid());
							return false;
						}
					}
					return true;
				}
	
}

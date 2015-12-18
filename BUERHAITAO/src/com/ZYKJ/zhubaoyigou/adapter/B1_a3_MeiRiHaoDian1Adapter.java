package com.ZYKJ.zhubaoyigou.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ZYKJ.zhubaoyigou.R;
import com.ZYKJ.zhubaoyigou.utils.AnimateFirstDisplayListener;
import com.ZYKJ.zhubaoyigou.utils.ImageOptions;
import com.ZYKJ.zhubaoyigou.utils.StringUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * @author lss 2015年6月24日 每日好店adapter
 *
 */
public class B1_a3_MeiRiHaoDian1Adapter  extends BaseAdapter {
	private Activity context;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	public B1_a3_MeiRiHaoDian1Adapter(Activity context, List<Map<String, String>> data) {
		this.context = context;
		this.data=data;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder ViewHolder=null;
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		if(convertView==null){
			ViewHolder=new ViewHolder();
			convertView=LinearLayout.inflate(context, R.layout.b1_a3_item, null);
			ViewHolder.im_a3_pic=(ImageView) convertView.findViewById(R.id.im_a3_pic);
			 LayoutParams params = ViewHolder.im_a3_pic.getLayoutParams();  
			 params.height=width/3-10;  
			 params.width =width/3;  
			 ViewHolder.im_a3_pic.setLayoutParams(params);  
			ViewHolder.tv_a3_storename=(TextView) convertView.findViewById(R.id.tv_a3_storename);
			ViewHolder.tv_a3_juli=(TextView) convertView.findViewById(R.id.tv_a3_juli);
			ViewHolder.comment_rating_bar=(RatingBar) convertView.findViewById(R.id.comment_rating_bar);
			ViewHolder.tv_a3_pinglunsum=(TextView) convertView.findViewById(R.id.tv_a3_pinglunsum);
			ViewHolder.tv_a3_dpfl=(TextView) convertView.findViewById(R.id.tv_a3_dpfl);
			ViewHolder.tv_a3_address=(TextView) convertView.findViewById(R.id.tv_a3_address);
			convertView.setTag(ViewHolder);
		}else{
			ViewHolder=(ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage((String)data.get(position).get("store_label"),  ViewHolder.im_a3_pic, ImageOptions.getOpstion(), animateFirstListener);
		
//		ImageLoader.getInstance().displayImage((String)data.get(position).get("store_label"), ViewHolder.im_a3_pic);
		ViewHolder.tv_a3_storename.setText(data.get(position).get("store_name"));
		ViewHolder.tv_a3_juli.setText(data.get(position).get("juli"));
		ViewHolder.comment_rating_bar.setRating(Float.parseFloat(data.get(position).get("store_desccredit")));
		ViewHolder.tv_a3_pinglunsum.setText(data.get(position).get("store_evaluate_count"));
		if (data.get(position).get("sc_name").equals("null")) {
			ViewHolder.tv_a3_dpfl.setText("");
		}else {
			ViewHolder.tv_a3_dpfl.setText(StringUtil.toString(data.get(position).get("sc_name"), ""));
		}
		ViewHolder.tv_a3_address.setText(data.get(position).get("store_address"));
		return convertView;
	}
	
	public final class ViewHolder {  
        public ImageView im_a3_pic;
        public TextView tv_a3_storename;
        public TextView tv_a3_juli;
        public RatingBar comment_rating_bar;
        public TextView tv_a3_pinglunsum;
        public TextView tv_a3_dpfl;
        public TextView tv_a3_address;
    }  
}

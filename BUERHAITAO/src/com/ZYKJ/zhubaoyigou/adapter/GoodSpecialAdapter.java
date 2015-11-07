package com.ZYKJ.zhubaoyigou.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ZYKJ.zhubaoyigou.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class GoodSpecialAdapter extends BaseAdapter {
	private Activity context;
	List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	public GoodSpecialAdapter(Activity context, List<Map<String, String>> data) {
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
		if(convertView==null){
			ViewHolder=new ViewHolder();
			convertView=LinearLayout.inflate(context, R.layout.goodspecial_item, null);
			ViewHolder.im_b1_a1_pic=(ImageView) convertView.findViewById(R.id.im_b1_a1_pic);
			ViewHolder.goods_id=(TextView) convertView.findViewById(R.id.goods_id);
			convertView.setTag(ViewHolder);
		}else{
			ViewHolder=(ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage((String)data.get(position).get("goods_image"), ViewHolder.im_b1_a1_pic);
		ViewHolder.goods_id.setText(data.get(position).get("goods_id"));
		return convertView;
	}
	
	public final class ViewHolder {  
       public ImageView im_b1_a1_pic;
       public TextView goods_id;
//       public TextView tv_a3_juli;
//       public RatingBar comment_rating_bar;
//       public TextView tv_a3_pinglunsum;
//       public TextView tv_a3_dpfl;
//       public TextView tv_a3_address;
   }  
}
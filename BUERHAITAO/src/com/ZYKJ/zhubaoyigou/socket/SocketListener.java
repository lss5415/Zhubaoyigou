package com.ZYKJ.zhubaoyigou.socket;

import com.ZYKJ.zhubaoyigou.data.ResultBean;


/**
 * 网路回调
 * 
 * @author bin
 * 
 */

public interface SocketListener {
	public void response(ResultBean result);
}

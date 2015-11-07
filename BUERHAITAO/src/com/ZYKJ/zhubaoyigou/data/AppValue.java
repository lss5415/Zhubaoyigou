package com.ZYKJ.zhubaoyigou.data;

import java.util.HashMap;
import java.util.Map;


public class AppValue {
	public static Shop shop_info = null;

	/**
	 * 存储的sharePerfence
	 */
	public static final String config = "config";
	/**
	 * 用户名
	 */
	public static final String USER_ID = "user_id";
	/**
	 * 密码
	 */
	public static final String USER_PASS = "user_pass";
	/**
	 * 当前的是否已经进行过指引
	 */
	public static final String IS_INTRO = "is_intro";
	
	/**
	 * 当前应用中存储的版本号
	 */
	public static final String VERSION = "version";
	/**
	 * 当前帐号是否已经登录的标识
	 */
	public static boolean is_login = false;

	/**
	 * 当前应用的存放目录
	 */
	public static final String FILE_DIR = "heyi_dir";
	/**
	 * 客户端类型
	 */
	public static final String CLIENT = "android";
	/**
	 * mob短信验证APPID
	 */
	public static final String APPID_mob = "b233fb8d8b30";
	/**
	 * mob短信验证SECRE
	 */
	public static final String APP_SECRE = "28fd31ec2a024f3aab619b1edd00057b";
	
	public static  Map<String, String> map_photo = new HashMap<String, String>();
	
	

}

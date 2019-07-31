package com.manage.core.exception;

public class ExceptionCode {
	
	/**
	 * 验证码错误
	 */
	public final static String login_captcha_error="login_captcha_error";
	
	/**
	 * 数据完整性校验失败
	 */
	public final static String login_incomplete_data="login_incomplete_data";
	/**
	 * 该账号已经停用
	 */
	public final static String login_user_disable="login_user_disable";
	/**
	 * 该账号已被锁定
	 */
	public final static String login_user_locking="login_user_locking";
	/**
	 * 该账号已被注销
	 */
	public final static String login_user_logout="login_user_logout";
	/**
	 * 登陆IP不在许可的网段范围内
	 */
	public final static String login_user_ip_error="login_user_ip_error";
	/**
	 * 当前时间无法登陆平台
	 */
	public final static String login_user_time_out="login_user_time_out";
	/**
	 * 用户名或密码错误
	 */
	public final static String login_user_error="login_user_error";
	/**
	 * 超出当前在线用户最大会话数
	 */
	public final static String login_max_limit="login_max_limit";
	
	/**
	 * 没有访问权限
	 */
	public final static String no_right="no_right";
	
	/**
	 * 获取系统配置异常
	 */
	public final static String read_sysconfig_error="read_sysconfig_error";
	/**
	 * 参数 错误
	 */
	public final static String wrong_param="wrong_param";
	 
 
}

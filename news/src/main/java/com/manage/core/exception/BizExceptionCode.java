package com.manage.core.exception;

public enum BizExceptionCode {

	FailSign("20001", "签名异常"), FailAmount("20002", "支付价格异常"), FailGift("20003", "礼物异常"), FailOrder("20004", "订单异常"),FailRecharge("20005","充值金额异常"),FailAccount("20006","账户异常"),ParamNull("20007","参数为空");

	private String code;
	private String msg;

	private BizExceptionCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

package com.zhidisoft.base;

/**
 * 封装服务器响应信息的基础类,当响应结果为json格式时，使用该基础类对数据进行封装
 * @author 卢健良
 *
 */
public class ResponseResult {

	private boolean success = true;
	
	private String msg = "";
	
	private Object result;
	
	public ResponseResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public ResponseResult(boolean success, String msg, Object result) {
		super();
		this.success = success;
		this.msg = msg;
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
}

package com.tzashinorpu.springsecuritydemo.pojo.vo;

public class ResponseWrapper {
	private Integer status;
	private String msg;
	private Object obj;

	public static ResponseWrapper build() {
		return new ResponseWrapper();
	}

	public static ResponseWrapper ok(String msg) {
		return new ResponseWrapper(200, msg, null);
	}

	public static ResponseWrapper ok(String msg, Object obj) {
		return new ResponseWrapper(200, msg, obj);
	}

	public static ResponseWrapper error(String msg) {
		return new ResponseWrapper(500, msg, null);
	}

	public static ResponseWrapper error(String msg, Object obj) {
		return new ResponseWrapper(500, msg, obj);
	}

	private ResponseWrapper() {
	}

	private ResponseWrapper(Integer status, String msg, Object obj) {
		this.status = status;
		this.msg = msg;
		this.obj = obj;
	}

	public Integer getStatus() {
		return status;
	}

	public ResponseWrapper setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseWrapper setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getObj() {
		return obj;
	}

	public ResponseWrapper setObj(Object obj) {
		this.obj = obj;
		return this;
	}
}

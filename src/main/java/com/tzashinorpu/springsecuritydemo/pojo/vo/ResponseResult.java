package com.tzashinorpu.springsecuritydemo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseResult {
	private Integer code;
	private String msg;
	private Object data;


	public static ResponseResult ok(String msg) {
		return new ResponseResult(200, msg, null);
	}

	public static ResponseResult ok(String msg, Object obj) {
		return new ResponseResult(200, msg, obj);
	}

	public static ResponseResult error(String msg) {
		return new ResponseResult(500, msg, null);
	}

	public static ResponseResult error(String msg, Object obj) {
		return new ResponseResult(500, msg, obj);
	}

	private ResponseResult() {
	}

	private ResponseResult(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}

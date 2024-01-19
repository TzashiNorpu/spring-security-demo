package com.tzashinorpu.springsecuritydemo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzashinorpu.springsecuritydemo.enums.ResponseEnums;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseResult {
	private Long code;
	private String msg;
	private Object data;


	private ResponseResult() {
	}

	private ResponseResult(Long code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ResponseResult ok(Object data) {
		return new ResponseResult(ResponseEnums.SUCCESS.getCode(), ResponseEnums.SUCCESS.getMsg(), data);
	}

	public static ResponseResult ok(String msg, Object obj) {
		return new ResponseResult(ResponseEnums.SUCCESS.getCode(), msg, obj);
	}

	public static ResponseResult error(String msg) {
		return new ResponseResult(ResponseEnums.FAILURE.getCode(), msg, null);
	}
	public static ResponseResult error(String code,String msg) {
		Long err_code = Long.valueOf(code);
		return new ResponseResult(err_code, msg, null);
	}


	public static ResponseResult error() {
		return new ResponseResult(ResponseEnums.ERROR.getCode(), ResponseEnums.ERROR.getMsg(), null);
	}

	public static ResponseResult error(Long code, String msg, Object data) {
		return new ResponseResult(code, msg, data);
	}
}

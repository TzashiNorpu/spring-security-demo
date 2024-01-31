package com.tzashinorpu.springsecuritydemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzashinorpu.springsecuritydemo.constant.enums.EnumBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;

/*
ControllerAdvice 中获取到这个异常后将其封装为 Response
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomException extends RuntimeException implements EnumBase {
	public static final Integer DEFAULT_ERROR_CODE = 999999;
	@Serial
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String msg;
	private Object data;

	public CustomException(String msg, Object data) {
		this.code = DEFAULT_ERROR_CODE;
		this.msg = msg;
		this.data = data;
	}

	public CustomException(EnumBase enums, Object data) {
		this.code = enums.getCode();
		this.msg = enums.getMsg();
		this.data = data;
	}

}

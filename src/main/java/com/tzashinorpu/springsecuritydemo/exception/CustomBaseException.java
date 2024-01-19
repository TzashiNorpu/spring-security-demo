package com.tzashinorpu.springsecuritydemo.exception;

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
public class CustomBaseException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;
	private Long code;
	private String message;
	private Object data;
}

package com.tzashinorpu.springsecuritydemo.utils;

import com.tzashinorpu.springsecuritydemo.exception.CustomBaseException;
import com.tzashinorpu.springsecuritydemo.exception.enums.CustomExceptionEnums;

public final class Assert {
	public static void shouldTrue(boolean expression, CustomExceptionEnums e, Object data) {
		if (!expression)
			throw new CustomBaseException(e.getCode(), e.getMsg(), data);
	}

	public static void shouldNotTrue(boolean expression, CustomExceptionEnums e, Object data) {
		if (expression)
			throw new CustomBaseException(e.getCode(), e.getMsg(), data);
	}

	public static void shouldNull(Object expression, CustomExceptionEnums e, Object data) {
		if (expression != null)
			throw new CustomBaseException(e.getCode(), e.getMsg(), data);
	}

	public static void shouldNotNull(Object expression, CustomExceptionEnums e, Object data) {
		if (expression == null)
			throw new CustomBaseException(e.getCode(), e.getMsg(), data);
	}
}

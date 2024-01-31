package com.tzashinorpu.springsecuritydemo.utils;

import com.tzashinorpu.springsecuritydemo.constant.enums.EnumBase;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;

public final class Assert {
	public static void shouldTrue(boolean expression, EnumBase e, Object data) {
		if (!expression)
			throw new CustomException(e.getCode(), e.getMsg(), data);
	}

	public static void shouldNotTrue(boolean expression, EnumBase e, Object data) {
		if (expression)
			throw new CustomException(e.getCode(), e.getMsg(), data);
	}

	public static void shouldNull(Object expression, EnumBase e, Object data) {
		if (expression != null)
			throw new CustomException(e.getCode(), e.getMsg(), data);
	}

	public static void shouldNotNull(Object expression, EnumBase e, Object data) {
		if (expression == null)
			throw new CustomException(e.getCode(), e.getMsg(), data);
	}
}

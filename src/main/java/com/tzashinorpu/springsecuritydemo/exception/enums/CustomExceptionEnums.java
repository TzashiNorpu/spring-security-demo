package com.tzashinorpu.springsecuritydemo.exception.enums;


public interface CustomExceptionEnums {
	Long DEFAULT_ERROR_CODE = 999L;
	Long getCode();

	String getMsg();
}

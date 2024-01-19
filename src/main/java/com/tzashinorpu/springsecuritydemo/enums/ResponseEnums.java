package com.tzashinorpu.springsecuritydemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnums implements BaseEnums {
	SUCCESS(200L, "交易成功"),
	FAILURE(500L, "交易失败"),
	ERROR(501L, "服务器内存错误");
	final Long code;
	final String msg;
}

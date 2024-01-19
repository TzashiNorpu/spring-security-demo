package com.tzashinorpu.springsecuritydemo.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseExceptionEnums implements CustomExceptionEnums {
	DATA_EXIST(10600L, "数据已经存在"),
	DATA_INSERT_FAILURE(10601L, "数据插入表失败"),
	DATA_DELETE_FAILURE(10603L, "数据表删除失败"),
	DATA_UPDATE_FAILURE(10604L, "数据表更新失败"),
	DATA_NOT_EXIST(10602L, "数据不存在");
	final Long code;
	final String msg;
}

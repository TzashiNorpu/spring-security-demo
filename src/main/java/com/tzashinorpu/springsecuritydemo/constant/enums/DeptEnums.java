package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum DeptEnums implements EnumBase {
	DEPT_EXIST("部门已经存在"),
	DEPT_NOT_EXIST("部门不存在"),
	DEPT_INSERT_FAILURE("部门插入失败"),
	DEPT_UPDATE_FAILURE("部门修改失败"),
	DEPT_DELETE_FAILURE("部门删除失败");

	final Integer code;
	final String msg;

	DeptEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.DEPT_INITIAL_ENUM_CODE;
	}
}

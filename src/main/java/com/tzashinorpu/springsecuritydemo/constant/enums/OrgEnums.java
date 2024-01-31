package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum OrgEnums implements EnumBase {
	ORG_EXIST("机构已经存在"),
	ORG_NOT_EXIST("机构不存在"),
	ORG_INSERT_FAILURE("机构插入失败"),
	ORG_UPDATE_FAILURE("机构修改失败"),
	ORG_DELETE_FAILURE("机构删除失败");

	final Integer code;
	final String msg;

	OrgEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.ORG_INITIAL_ENUM_CODE;
	}


}

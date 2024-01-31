package com.tzashinorpu.springsecuritydemo.constant.enums;

import com.tzashinorpu.springsecuritydemo.constant.Constants;
import lombok.Getter;

@Getter
public enum RoleOrgEnums implements EnumBase {
	ROLE_ORG_EXIST("机构对应的权限已经存在"),
	ROLE_ORG_NOT_EXIST("机构对应的权限不存在"),
	ROLE_ORG_INSERT_FAILURE("机构绑定权限插入失败"),
	ROLE_ORG_UPDATE_FAILURE("机构绑定权限修改失败"),
	ROLE_ORG_DELETE_FAILURE("机构绑定权限删除失败");

	final Integer code;
	final String msg;

	RoleOrgEnums(String msg) {
		this.code = Counter.nextValue;
		Counter.nextValue = code + 1;
		this.msg = msg;
	}

	private static class Counter {
		static int nextValue = Constants.ROLE_ORG_INITIAL_ENUM_CODE;
	}
}

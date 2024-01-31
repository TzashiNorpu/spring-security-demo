package com.tzashinorpu.springsecuritydemo.pojo.dto;

import com.tzashinorpu.springsecuritydemo.constant.enums.UserEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserRoleDTO {
	private String userCode;
	@Setter
	private Integer roleCode;


	public void setUserCode(String userCode) {
		if (userCode == null || userCode.length() != 5)
			throw new CustomException(UserEnums.USER_CODE_FORMAT_ERROR, userCode);
		this.userCode = userCode;
	}

}

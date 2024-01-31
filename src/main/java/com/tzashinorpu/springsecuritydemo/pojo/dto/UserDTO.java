package com.tzashinorpu.springsecuritydemo.pojo.dto;

import com.tzashinorpu.springsecuritydemo.constant.enums.UserEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserDTO {
	private String userCode;
	@Setter
	private String username;
	@Setter
	private String password;
	@Setter
	private String deptCode;
	@Setter
	private String orgCode;
	@Setter
	private String phone;
	@Setter
	private String email;

	public void setUserCode(String userCode) {
		if (userCode == null || userCode.length() != 5)
			throw new CustomException(UserEnums.USER_CODE_FORMAT_ERROR, userCode);
		this.userCode = userCode;
	}
}

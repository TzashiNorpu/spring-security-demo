package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserRoleVO extends BaseVO<UserRoleVO> {
	private String userCode;
	private String userName;
	private Integer roleCode;
	private String roleName;

	public UserRoleVO(String userCode, String userName, Integer roleCode, String roleName) {
		super(new UserRoleVO(userCode, userName, roleCode, roleName));
	}
}

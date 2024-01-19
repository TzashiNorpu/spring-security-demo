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

	public UserRoleVO(Long userId, String userName, Long roleId, String roleName) {
		super(new UserRoleVO(userId, userName, roleId, roleName));
	}
}

package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class RoleVO extends BaseVO<RoleVO> {
	private Integer roleCode;
	private String roleName;

	public RoleVO(Long roleId, String roleName) {
		super(new RoleVO(roleId, roleName));
	}
}

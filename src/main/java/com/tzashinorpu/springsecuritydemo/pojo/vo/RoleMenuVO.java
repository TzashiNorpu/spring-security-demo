package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class RoleMenuVO extends BaseVO<RoleMenuVO> {
	private Integer roleCode;
	private Integer menuCode;

	public RoleMenuVO(Integer roleCode, Integer menuCode) {
		super(new RoleMenuVO(roleCode, menuCode));
	}
}

package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrgRoleVO extends BaseVO<OrgRoleVO> {
	private Integer roleCode;
	private String orgCode;

	public OrgRoleVO(Integer roleCode, String orgCode) {
		super(new OrgRoleVO(roleCode, orgCode));
	}
}

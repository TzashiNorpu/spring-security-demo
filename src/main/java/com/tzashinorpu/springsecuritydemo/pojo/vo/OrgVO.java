package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrgVO extends BaseVO<OrgVO> {
	private String orgCode;
	private String orgName;

	public OrgVO(String orgCode, String orgName) {
		super(new OrgVO(orgCode, orgName));
	}
}

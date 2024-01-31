package com.tzashinorpu.springsecuritydemo.pojo.dto;

import lombok.Data;

@Data
public class OrgDTO {
	private String orgCode;
	private String orgName;
	private String parentCode;
	private String ancestors;
	private Short orderNum;
}

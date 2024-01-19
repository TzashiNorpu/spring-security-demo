package com.tzashinorpu.springsecuritydemo.pojo.dto;

import lombok.Data;

@Data
public class DeptDTO {
	private String deptCode;
	private String deptName;
	private String orgCode;
	private String ancestors;
}

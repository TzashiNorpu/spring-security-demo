package com.tzashinorpu.springsecuritydemo.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RoleDTO {
	private Integer roleCode;
	private String roleName;
	private String roleKey;
	private Short orderNum;
	private String dataScope;
	private Boolean menuCheckStrictly;
	private Boolean deptCheckStrictly;
}

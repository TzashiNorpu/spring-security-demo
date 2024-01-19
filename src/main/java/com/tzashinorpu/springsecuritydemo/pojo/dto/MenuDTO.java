package com.tzashinorpu.springsecuritydemo.pojo.dto;

import lombok.Data;

@Data
public class MenuDTO {
	private Integer menuCode;
	private String menuName;
	private Integer parentCode;
	private Short orderNum;
	private String path;
	private String component;
	private String query;
	private Boolean frame;
	private Boolean cache;
	private String menuType;
	private String visible;
	private String status;
	private String perms;
	private String icon;
	private String remark;
}

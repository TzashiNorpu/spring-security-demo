package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVO extends BaseVO{
	private Long roleId;
}

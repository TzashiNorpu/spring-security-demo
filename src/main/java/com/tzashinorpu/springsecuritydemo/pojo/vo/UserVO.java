package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends BaseVO{
	private Long userId;
}

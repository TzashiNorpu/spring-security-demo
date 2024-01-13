package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Data;

@Data
public class BaseVO {
	// 业务状态
	Long code;
	// 业务描述
	String msg;

	public BaseVO(Long id, String msg) {
		this.code = id;
		this.msg = msg;
	}

	public BaseVO() {
	}
}

package com.tzashinorpu.springsecuritydemo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// BaseVO 返回正常交易的数据，在 ControllerAdvice 中增加 code 和 msg
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO<T> {
	// 返回数据
	T data;

	public BaseVO(T data) {
		this.data = data;
	}

	public BaseVO() {
	}
}

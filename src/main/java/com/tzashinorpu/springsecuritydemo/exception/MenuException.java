package com.tzashinorpu.springsecuritydemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzashinorpu.springsecuritydemo.exception.enums.CustomExceptionEnums;
import com.tzashinorpu.springsecuritydemo.pojo.vo.MenuVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuException extends CustomBaseException {


	public MenuException(String msg, MenuVO menu) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, menu);
	}

	public MenuException(String msg, List<MenuVO> menus) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, menus);
	}
}

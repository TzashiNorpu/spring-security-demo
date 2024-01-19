package com.tzashinorpu.springsecuritydemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzashinorpu.springsecuritydemo.exception.enums.CustomExceptionEnums;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleException extends CustomBaseException {


	public RoleException(String msg, RoleVO role) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, role);
	}

	public RoleException(String msg, List<RoleVO> roles) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, roles);
	}
}

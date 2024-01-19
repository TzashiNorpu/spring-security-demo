package com.tzashinorpu.springsecuritydemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzashinorpu.springsecuritydemo.exception.enums.CustomExceptionEnums;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserRoleVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleException extends CustomBaseException {
	public UserRoleException(String msg, UserRoleVO vo) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, vo);
	}

	public UserRoleException(String msg, List<UserRoleVO> vos) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, vos);
	}
}

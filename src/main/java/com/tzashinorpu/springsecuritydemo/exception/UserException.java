package com.tzashinorpu.springsecuritydemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzashinorpu.springsecuritydemo.exception.enums.CustomExceptionEnums;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserException extends CustomBaseException {


	public UserException(String msg, UserVO user) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, user);
	}

	public UserException(String msg, List<UserVO> users) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, users);
	}
}

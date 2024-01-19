package com.tzashinorpu.springsecuritydemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzashinorpu.springsecuritydemo.exception.enums.CustomExceptionEnums;
import com.tzashinorpu.springsecuritydemo.pojo.vo.DeptVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeptException extends CustomBaseException {


	public DeptException(String msg, DeptVO vo) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, vo);
	}

	public DeptException(String msg, List<DeptVO> vos) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, vos);
	}
}

package com.tzashinorpu.springsecuritydemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tzashinorpu.springsecuritydemo.exception.enums.CustomExceptionEnums;
import com.tzashinorpu.springsecuritydemo.pojo.vo.OrgVO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgException extends CustomBaseException {


	public OrgException(String msg, OrgVO vo) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, vo);
	}

	public OrgException(String msg, List<OrgVO> vos) {
		super(CustomExceptionEnums.DEFAULT_ERROR_CODE, msg, vos);
	}
}

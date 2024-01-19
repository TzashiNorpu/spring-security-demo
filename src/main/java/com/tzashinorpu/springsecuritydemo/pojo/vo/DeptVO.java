package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class DeptVO extends BaseVO<DeptVO> {
	private String deptCode;
	private String deptName;

	public DeptVO(Long deptCode, String deptName) {
		super(new DeptVO(deptCode, deptName));
	}
}

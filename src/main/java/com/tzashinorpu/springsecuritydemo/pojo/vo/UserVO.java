package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserVO extends BaseVO<UserVO> {
	private String userCode;
	private String userName;

	public UserVO(String userCode, String userName) {
		super(new UserVO(userCode, userName));
	}
}

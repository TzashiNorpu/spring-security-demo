package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserVO extends BaseVO<UserVO> {
	private Long userId;
	private String userName;

	public UserVO(Long userId, String userName) {
		super(new UserVO(userId, userName));
	}
}

package com.tzashinorpu.springsecuritydemo.pojo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class MenuVO extends BaseVO<MenuVO> {
	private Integer menuCode;
	private String menuName;

	public MenuVO(Integer menuCode, String menuName) {
		super(new MenuVO(menuCode, menuName));
	}
}

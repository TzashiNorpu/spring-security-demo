package com.tzashinorpu.springsecuritydemo.controller;

import com.tzashinorpu.springsecuritydemo.pojo.dto.MenuDTO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysMenuPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.MenuVO;
import com.tzashinorpu.springsecuritydemo.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-18
 */
@RestController
public class MenuController {
	@Autowired
	SysMenuService sysMenuService;

	@PostMapping("/menu")
	public MenuVO addMenu(@RequestBody MenuDTO menuDTO) {
		SysMenuPO menuPO = new SysMenuPO();
		menuPO.setMenuCode(menuDTO.getMenuCode());
		menuPO.setMenuName(menuDTO.getMenuName());
		menuPO.setMenuType(menuDTO.getMenuType());
		menuPO.setIcon(menuDTO.getIcon());
		menuPO.setCache(menuDTO.getCache());
		menuPO.setFrame(menuDTO.getFrame());
		menuPO.setComponent(menuDTO.getComponent());
		menuPO.setPath(menuDTO.getPath());
		menuPO.setPerms(menuPO.getPerms());
		menuPO.setQuery(menuDTO.getQuery());
		menuPO.setRemark(menuDTO.getRemark());
		menuPO.setStatus(menuDTO.getStatus());
		menuPO.setVisible(menuDTO.getVisible());
		menuPO.setOrderNum(menuDTO.getOrderNum());
		menuPO.setParentCode(menuDTO.getParentCode());
		return sysMenuService.addMenu(menuPO);
	}

	@PostMapping("/menus")
	public List<MenuVO> batchAdd(@RequestBody List<MenuDTO> list) {
		ArrayList<SysMenuPO> poList = new ArrayList<>();
		list.forEach(item -> {
			SysMenuPO menuPO = new SysMenuPO();
			menuPO.setMenuCode(item.getMenuCode());
			menuPO.setMenuName(item.getMenuName());
			menuPO.setMenuType(item.getMenuType());
			menuPO.setIcon(item.getIcon());
			menuPO.setCache(item.getCache());
			menuPO.setFrame(item.getFrame());
			menuPO.setComponent(item.getComponent());
			menuPO.setPath(item.getPath());
			menuPO.setPerms(item.getPerms());
			menuPO.setQuery(item.getQuery());
			menuPO.setRemark(item.getRemark());
			menuPO.setStatus(item.getStatus());
			menuPO.setVisible(item.getVisible());
			menuPO.setOrderNum(item.getOrderNum());
			menuPO.setParentCode(item.getParentCode());
			poList.add(menuPO);
		});
		return sysMenuService.batchAdd(poList);
	}
}

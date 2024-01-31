package com.tzashinorpu.springsecuritydemo.service;

import com.tzashinorpu.springsecuritydemo.pojo.po.SysRoleMenuPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleMenuVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-23
 */
public interface SysRoleMenuService extends IService<SysRoleMenuPO> {
	RoleMenuVO bindMenuToRole(SysRoleMenuPO po) ;

	List<RoleMenuVO> bindMenusToRole(ArrayList<SysRoleMenuPO> list);
}

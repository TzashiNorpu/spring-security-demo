package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.exception.MenuException;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysMenuPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-18
 */
public interface SysMenuService extends IService<SysMenuPO> {
	MenuVO addMenu(SysMenuPO po) throws MenuException;

	List<MenuVO> batchAdd(List<SysMenuPO> list);
}

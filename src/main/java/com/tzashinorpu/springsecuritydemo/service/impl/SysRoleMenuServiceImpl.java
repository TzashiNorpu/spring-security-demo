package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.constant.enums.MenuEnums;
import com.tzashinorpu.springsecuritydemo.constant.enums.RoleEnums;
import com.tzashinorpu.springsecuritydemo.constant.enums.RoleMenuEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;
import com.tzashinorpu.springsecuritydemo.mapper.SysRoleMenuMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysMenuPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRoleMenuPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.RoleMenuVO;
import com.tzashinorpu.springsecuritydemo.service.SysRoleMenuService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-23
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuPO> implements SysRoleMenuService {
	@Override
	public RoleMenuVO bindMenuToRole(SysRoleMenuPO po) {
		RoleMenuVO vo = new RoleMenuVO();
		Integer menuCode = po.getMenuCode();
		vo.setMenuCode(menuCode);
		LambdaQueryChainWrapper<SysMenuPO> menuWrapper = new LambdaQueryChainWrapper<>(SysMenuPO.class);
		boolean menuExists = menuWrapper.eq(SysMenuPO::getMenuCode, menuCode).exists();
		Assert.shouldTrue(menuExists, MenuEnums.MENU_NOT_EXIST, vo);
		Integer roleCode = po.getRoleCode();
		vo.setRoleCode(roleCode);
		LambdaQueryChainWrapper<SysRolePO> roleWrapper = new LambdaQueryChainWrapper<>(SysRolePO.class);
		boolean roleExists = roleWrapper.eq(SysRolePO::getRoleCode, roleCode).exists();
		Assert.shouldTrue(roleExists, RoleEnums.ROLE_NOT_EXIST, vo);


		boolean exists = lambdaQuery().eq(SysRoleMenuPO::getRoleCode, roleCode).eq(SysRoleMenuPO::getMenuCode, menuCode).exists();
		Assert.shouldNotTrue(exists, RoleMenuEnums.ROLE_MENU_EXIST, vo);

		try {
			boolean inserted = po.insert();
			Assert.shouldTrue(inserted, RoleMenuEnums.ROLE_MENU_INSERT_FAILURE, vo);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vo);
		}
		return vo;
	}

	@Override
	public List<RoleMenuVO> bindMenusToRole(ArrayList<SysRoleMenuPO> list) {
		ArrayList<RoleMenuVO> vos = new ArrayList<>();
		list.forEach(item -> {
			RoleMenuVO vo = new RoleMenuVO();
			vo.setRoleCode(item.getRoleCode());
			vo.setMenuCode(item.getMenuCode());
			vos.add(vo);
		});
		try {
			boolean saved = this.saveBatch(list);
			Assert.shouldTrue(saved, RoleMenuEnums.ROLE_MENU_INSERT_FAILURE, vos);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vos);
		}
		return vos;
	}
}

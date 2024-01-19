package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.exception.MenuException;
import com.tzashinorpu.springsecuritydemo.exception.enums.BaseExceptionEnums;
import com.tzashinorpu.springsecuritydemo.mapper.SysMenuMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysMenuPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.MenuVO;
import com.tzashinorpu.springsecuritydemo.service.SysMenuService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuPO> implements SysMenuService {

	@Override
	public MenuVO addMenu(SysMenuPO po) throws MenuException {
		MenuVO vo = new MenuVO();
		vo.setMenuCode(po.getMenuCode());
		vo.setMenuName(po.getMenuName());
		boolean exists = lambdaQuery().eq(SysMenuPO::getMenuCode, po.getMenuCode()).exists();
		Assert.shouldNotTrue(exists, BaseExceptionEnums.DATA_EXIST,vo);
		try {
			boolean save = this.save(po);
			Assert.shouldTrue(save,BaseExceptionEnums.DATA_INSERT_FAILURE,vo);
		} catch (Exception e) {
			throw new MenuException(e.getMessage(), vo);
		}
		return vo;
	}

	@Override
	public List<MenuVO> batchAdd(List<SysMenuPO> list) {
		ArrayList<MenuVO> menuVOS = new ArrayList<>();
		list.forEach(item -> {
			MenuVO vo = new MenuVO();
			vo.setMenuCode(item.getMenuCode());
			vo.setMenuName(item.getMenuName());
			menuVOS.add(vo);
		});
		try {
			boolean b = this.saveBatch(list);
			Assert.shouldTrue(b, BaseExceptionEnums.DATA_INSERT_FAILURE, menuVOS);
		} catch (Exception e) {
			throw new MenuException(e.getMessage(), menuVOS);
		}
		return menuVOS;
	}
}

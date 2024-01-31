package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.constant.enums.OrgEnums;
import com.tzashinorpu.springsecuritydemo.constant.enums.RoleEnums;
import com.tzashinorpu.springsecuritydemo.constant.enums.RoleOrgEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;
import com.tzashinorpu.springsecuritydemo.mapper.SysOrgRoleMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysRolePO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.OrgRoleVO;
import com.tzashinorpu.springsecuritydemo.service.SysOrgRoleService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色和机构关联表 服务实现类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-24
 */
@Service
public class SysOrgRoleServiceImpl extends ServiceImpl<SysOrgRoleMapper, SysOrgRolePO> implements SysOrgRoleService {


	@Override
	public OrgRoleVO bindRoleToOrg(SysOrgRolePO po) {
		OrgRoleVO vo = new OrgRoleVO();
		Integer roleCode = po.getRoleCode();
		vo.setRoleCode(roleCode);
		LambdaQueryChainWrapper<SysRolePO> roleWrapper = new LambdaQueryChainWrapper<>(SysRolePO.class);
		boolean roleExists = roleWrapper.eq(SysRolePO::getRoleCode, roleCode).exists();
		Assert.shouldTrue(roleExists, RoleEnums.ROLE_NOT_EXIST, vo);

		String orgCode = po.getOrgCode();
		vo.setOrgCode(orgCode);
		LambdaQueryChainWrapper<SysOrgPO> orgWrapper = new LambdaQueryChainWrapper<>(SysOrgPO.class);
		boolean orgExists = orgWrapper.eq(SysOrgPO::getOrgCode, orgCode).exists();
		Assert.shouldTrue(orgExists, OrgEnums.ORG_NOT_EXIST, vo);


		boolean exists = lambdaQuery().eq(SysOrgRolePO::getRoleCode, roleCode).eq(SysOrgRolePO::getOrgCode, orgCode).exists();
		Assert.shouldNotTrue(exists, RoleOrgEnums.ROLE_ORG_EXIST, vo);

		try {
			boolean inserted = po.insert();
			Assert.shouldTrue(inserted, RoleOrgEnums.ROLE_ORG_INSERT_FAILURE, vo);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vo);
		}
		return vo;
	}

	@Override
	public List<OrgRoleVO> bindRolesToOrg(ArrayList<SysOrgRolePO> list) {
		ArrayList<OrgRoleVO> vos = new ArrayList<>();
		list.forEach(item -> {
			OrgRoleVO vo = new OrgRoleVO();
			vo.setRoleCode(item.getRoleCode());
			vo.setOrgCode(item.getOrgCode());
			vos.add(vo);
		});
		try {
			boolean saved = this.saveBatch(list);
			Assert.shouldTrue(saved, RoleOrgEnums.ROLE_ORG_INSERT_FAILURE, vos);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vos);
		}
		return vos;
	}
}

package com.tzashinorpu.springsecuritydemo.service;

import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgRolePO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.vo.OrgRoleVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色和机构关联表 服务类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-24
 */
public interface SysOrgRoleService extends IService<SysOrgRolePO> {


	List<OrgRoleVO> bindRolesToOrg(ArrayList<SysOrgRolePO> list);

	OrgRoleVO bindRoleToOrg(SysOrgRolePO po);
}

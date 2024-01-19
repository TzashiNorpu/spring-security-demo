package com.tzashinorpu.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysMenuPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.OrgVO;

import java.util.List;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
public interface SysOrgService extends IService<SysOrgPO> {
	OrgVO addOrg(SysOrgPO po);

	List<OrgVO> batchAdd(List<SysOrgPO> list);
}

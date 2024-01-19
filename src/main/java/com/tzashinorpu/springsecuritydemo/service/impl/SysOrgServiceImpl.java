package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.exception.OrgException;
import com.tzashinorpu.springsecuritydemo.exception.enums.BaseExceptionEnums;
import com.tzashinorpu.springsecuritydemo.mapper.SysOrgMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.OrgVO;
import com.tzashinorpu.springsecuritydemo.service.SysOrgService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrgPO> implements SysOrgService {

	@Override
	public OrgVO addOrg(SysOrgPO po) {
		OrgVO vo = new OrgVO();
		String orgCode = po.getOrgCode();
		vo.setOrgCode(orgCode);
		vo.setOrgName(po.getOrgName());
		boolean exists = lambdaQuery().eq(SysOrgPO::getOrgCode, orgCode).exists();
		Assert.shouldNotTrue(exists, BaseExceptionEnums.DATA_EXIST, vo);
		try {
			boolean save = this.save(po);
			Assert.shouldTrue(save, BaseExceptionEnums.DATA_INSERT_FAILURE, vo);
		} catch (Exception e) {
			throw new OrgException(e.getMessage(), vo);
		}
		return vo;
	}

	@Override
	public List<OrgVO> batchAdd(List<SysOrgPO> list) {
		ArrayList<OrgVO> vos = new ArrayList<>();
		list.forEach(item -> {
			OrgVO vo = new OrgVO();
			vo.setOrgCode(item.getOrgCode());
			vo.setOrgName(item.getOrgName());
			vos.add(vo);
		});
		boolean saved = false;
		try {
			saved = this.saveBatch(list);
			Assert.shouldTrue(saved, BaseExceptionEnums.DATA_INSERT_FAILURE, vos);
		} catch (Exception e) {
			throw new OrgException(e.getMessage(), vos);
		}
		return vos;
	}
}

package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.constant.enums.OrgEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;
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
		Assert.shouldNotTrue(exists, OrgEnums.ORG_EXIST, vo);
		try {
			boolean save = this.save(po);
			Assert.shouldTrue(save, OrgEnums.ORG_INSERT_FAILURE, vo);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vo);
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
		try {
			boolean saved = this.saveBatch(list);
			Assert.shouldTrue(saved, OrgEnums.ORG_INSERT_FAILURE, vos);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vos);
		}
		return vos;
	}
}

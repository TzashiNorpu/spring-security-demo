package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.exception.DeptException;
import com.tzashinorpu.springsecuritydemo.exception.enums.BaseExceptionEnums;
import com.tzashinorpu.springsecuritydemo.mapper.SysDeptMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysDeptPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.DeptVO;
import com.tzashinorpu.springsecuritydemo.service.SysDeptService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptPO> implements SysDeptService {

	@Override
	public DeptVO addDept(SysDeptPO po) {
		DeptVO vo = new DeptVO();
		String code = po.getDeptCode();
		String name = po.getDeptName();
		vo.setDeptCode(code);
		vo.setDeptName(name);
		boolean exists = lambdaQuery().eq(SysDeptPO::getDeptCode, code).exists();
		Assert.shouldNotTrue(exists, BaseExceptionEnums.DATA_EXIST, vo);
		try {
			boolean inserted = po.insert();
			Assert.shouldTrue(inserted, BaseExceptionEnums.DATA_INSERT_FAILURE, vo);
		} catch (Exception e) {
			throw new DeptException(e.getMessage(), vo);
		}
		return vo;
	}

	@Override
	public List<DeptVO> batchAdd(List<SysDeptPO> list) {
		ArrayList<DeptVO> deptVOS = new ArrayList<>();
		list.forEach(item -> {
			DeptVO vo = new DeptVO();
			vo.setDeptCode(item.getDeptCode());
			vo.setDeptName(item.getDeptName());
			deptVOS.add(vo);
		});
		try {
			boolean b = this.saveBatch(list);
			Assert.shouldTrue(b, BaseExceptionEnums.DATA_INSERT_FAILURE, deptVOS);
		} catch (Exception e) {
			throw new DeptException(e.getMessage(), deptVOS);
		}
		return deptVOS;
	}
}

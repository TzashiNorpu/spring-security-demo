package com.tzashinorpu.springsecuritydemo.service;

import com.tzashinorpu.springsecuritydemo.pojo.po.SysDeptPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.DeptVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.OrgVO;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
public interface SysDeptService extends IService<SysDeptPO> {
	DeptVO addDept(SysDeptPO po);

	List<DeptVO> batchAdd(List<SysDeptPO> list);
}

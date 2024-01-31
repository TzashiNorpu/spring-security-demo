package com.tzashinorpu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tzashinorpu.springsecuritydemo.constant.enums.OrgEnums;
import com.tzashinorpu.springsecuritydemo.constant.enums.UserEnums;
import com.tzashinorpu.springsecuritydemo.exception.CustomException;
import com.tzashinorpu.springsecuritydemo.mapper.SysUserMapper;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysOrgPO;
import com.tzashinorpu.springsecuritydemo.pojo.po.SysUserPO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserInfoVO;
import com.tzashinorpu.springsecuritydemo.pojo.vo.UserVO;
import com.tzashinorpu.springsecuritydemo.service.SysUserService;
import com.tzashinorpu.springsecuritydemo.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPO> implements SysUserService {

	@Override
	public UserVO addUser(SysUserPO po) {
		UserVO vo = new UserVO();
		String username = po.getUsername();
		boolean exists = lambdaQuery().eq(SysUserPO::getUsername, username).exists();
		vo.setUserName(username);
		Assert.shouldNotTrue(exists, UserEnums.USER_EXIST, vo);

		LambdaQueryChainWrapper<SysOrgPO> orgWrapper = new LambdaQueryChainWrapper<>(SysOrgPO.class);
		String orgCode = po.getOrgCode();
		boolean orgExists = orgWrapper.eq(SysOrgPO::getOrgCode, orgCode).exists();
		Assert.shouldTrue(orgExists, OrgEnums.ORG_EXIST, orgCode);

		try {
			boolean save = this.save(po);
			Assert.shouldTrue(save, UserEnums.USER_INSERT_FAILURE, vo);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vo);
		}
		SysUserPO user = lambdaQuery().eq(SysUserPO::getUsername, username).one();
		vo.setUserCode(user.getUserCode());
		return vo;
	}

	@Override
	public List<UserVO> batchAdd(List<SysUserPO> pos) {
		ArrayList<UserVO> vos = new ArrayList<>();
		pos.forEach(item -> {
			UserVO vo = new UserVO();
			vo.setUserCode(item.getUserCode());
			vo.setUserName(item.getUsername());
			vos.add(vo);
		});
		try {
			boolean saved = this.saveBatch(pos);
			Assert.shouldTrue(saved, UserEnums.USER_INSERT_FAILURE, vos);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new CustomException(e.getMessage(), vos);
		}
		return vos;
	}

	@Override
	public UserVO updateUser(SysUserPO po) {
		return null;
	}

	@Override
	public UserVO delUser(SysUserPO po) {
		UserVO vo = new UserVO();
		vo.setUserName(po.getUsername());
		SysUserPO user = lambdaQuery().eq(SysUserPO::getUsername, po.getUsername()).one();
		Assert.shouldNotNull(user, UserEnums.USER_NOT_EXIST, vo);
		vo.setUserCode(user.getUserCode());
		try {
			boolean remove = this.remove(lambdaQuery(user));
			Assert.shouldTrue(remove, UserEnums.USER_DELETE_FAILURE, vo);
			return vo;
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), vo);
		}
	}

	@Override
	public UserInfoVO getUser(SysUserPO po) {
		return null;
	}
}

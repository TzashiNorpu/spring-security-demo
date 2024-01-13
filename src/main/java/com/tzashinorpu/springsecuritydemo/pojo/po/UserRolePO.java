package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@TableName("sys_user_role")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class UserRolePO extends BasePO<UserRolePO>{
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	private Long userId;
	private Long roleId;
	@Version
	private Integer version;
}

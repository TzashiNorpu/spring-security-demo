package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user_role")
@Schema(name = "SysUserRolePO对象", description = "用户和角色关联表")
public class SysUserRolePO extends BasePO<SysUserRolePO> {

	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(description = "员工工号")
	@TableField("user_code")
	private String userCode;

	@Schema(description = "角色编码")
	@TableField("role_code")
	private Integer roleCode;

	@Override
	public Serializable pkVal() {
		return null;
	}
}

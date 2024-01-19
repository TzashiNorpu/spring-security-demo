package com.tzashinorpu.springsecuritydemo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name = "SysUserPO对象", description = "系统用户表")
public class SysUserPO extends BasePO<SysUserPO> {

	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(description = "员工工号")
	@TableField("user_code")
	private String userCode;

	@Schema(description = "密文密码")
	@TableField("password")
	private String password;

	@Schema(description = "用户名")
	@TableField("username")
	private String username;

	@Schema(description = "员工所属部门编码")
	@TableField("dept_code")
	private String deptCode;

	@Schema(description = "员工所属机构编码")
	@TableField("org_code")
	private String orgCode;

	@Schema(description = "用户邮箱")
	@TableField("email")
	private String email;

	@Schema(description = "手机号码")
	@TableField("phone")
	private String phone;

	@Schema(description = "头像地址")
	@TableField("avatar")
	private String avatar;

	@Schema(description = "用户是否过期")
	@TableField("account_non_expired")
	private Boolean accountNonExpired;

	@Schema(description = "用户是否锁定")
	@TableField("account_non_locked")
	private Boolean accountNonLocked;

	@Schema(description = "密码是否过期")
	@TableField("credentials_non_expired")
	private Boolean credentialsNonExpired;

	@Schema(description = "用户是否有效")
	@TableField("enabled")
	private Boolean enabled;

	@Schema(description = "乐观锁版本号")
	@TableField("version")
	@Version
	private Long version;

	@Override
	public Serializable pkVal() {
		return null;
	}
}

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
 * 角色和机构关联表
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_org_role")
@Schema(name = "SysRoleOrgPO对象", description = "角色和机构关联表")
public class SysOrgRolePO extends BasePO<SysOrgRolePO> {

	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(description = "角色编码")
	@TableField("role_code")
	private Integer roleCode;

	@Schema(description = "机构编码")
	@TableField("org_code")
	private String orgCode;

	@Schema(description = "乐观锁版本号")
	@TableField("version")
	@Version
	private Long version;

	@Override
	public Serializable pkVal() {
		return null;
	}
}

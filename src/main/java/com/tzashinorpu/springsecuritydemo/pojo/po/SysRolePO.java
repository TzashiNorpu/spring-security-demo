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
 * 角色信息表
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role")
@Schema(name = "SysRolePO对象", description = "角色信息表")
public class SysRolePO extends BasePO<SysRolePO> {

	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(description = "角色编码")
	@TableField("role_code")
	private Integer roleCode;

	@Schema(description = "角色名称")
	@TableField("role_name")
	private String roleName;

	@Schema(description = "角色权限字符串")
	@TableField("role_key")
	private String roleKey;

	@Schema(description = "显示顺序")
	@TableField("order_num")
	private Short orderNum;

	@Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
	@TableField("data_scope")
	private String dataScope;

	@Schema(description = "菜单树选择项是否关联显示")
	@TableField("menu_check_strictly")
	private Boolean menuCheckStrictly;

	@Schema(description = "部门树选择项是否关联显示")
	@TableField("dept_check_strictly")
	private Boolean deptCheckStrictly;

	@Schema(description = "角色状态（0正常 1停用）")
	@TableField("status")
	private String status;

	@Schema(description = "备注")
	@TableField("remark")
	private String remark;

	@Schema(description = "乐观锁版本号")
	@TableField("version")
	@Version
	private Long version;

	@Override
	public Serializable pkVal() {
		return null;
	}
}

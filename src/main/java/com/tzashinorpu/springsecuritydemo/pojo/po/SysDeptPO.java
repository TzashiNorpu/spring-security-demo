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
 * 部门表
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_dept")
@Schema(name = "SysDeptPO对象", description = "部门表")
public class SysDeptPO extends BasePO<SysDeptPO> {

	@Serial
  private static final long serialVersionUID = 1L;

	@Schema(description = "部门编码")
	@TableField("dept_code")
	private String deptCode;

	@Schema(description = "部门名称")
	@TableField("dept_name")
	private String deptName;

	@Schema(description = "部门所属机构编码")
	@TableField("org_code")
	private String orgCode;

	@Schema(description = "祖级列表")
	@TableField("ancestors")
	private String ancestors;

	@Schema(description = "显示顺序")
	@TableField("order_num")
	private Short orderNum;

	@Schema(description = "状态（0正常 1停用）")
	@TableField("status")
	private String status;

	@Schema(description = "乐观锁版本号")
	@TableField("version")
	@Version
	private Long version;

	@Override
	public Serializable pkVal() {
		return null;
	}
}

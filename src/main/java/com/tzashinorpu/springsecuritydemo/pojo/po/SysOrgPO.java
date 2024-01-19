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
 * 机构表
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_org")
@Schema(name = "SysOrgPO对象", description = "机构表")
public class SysOrgPO extends BasePO<SysOrgPO> {

	@Serial
  private static final long serialVersionUID = 1L;

	@Schema(description = "机构号")
	@TableField("org_code")
	private String orgCode;

	@Schema(description = "机构名称")
	@TableField("org_name")
	private String orgName;

	@Schema(description = "上级机构号")
	@TableField("parent_code")
	private String parentCode;

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

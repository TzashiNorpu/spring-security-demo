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
 * 菜单权限表
 * </p>
 *
 * @author tzashinorpu
 * @since 2024-01-19
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_menu")
@Schema(name = "SysMenuPO对象", description = "菜单权限表")
public class SysMenuPO extends BasePO<SysMenuPO> {

	@Serial
  private static final long serialVersionUID = 1L;

	@Schema(description = "菜单编码")
	@TableField("menu_code")
	private Integer menuCode;

	@Schema(description = "菜单名称")
	@TableField("menu_name")
	private String menuName;

	@Schema(description = "父菜单编码")
	@TableField("parent_code")
	private Integer parentCode;

	@Schema(description = "显示顺序")
	@TableField("order_num")
	private Short orderNum;

	@Schema(description = "路由地址")
	@TableField("path")
	private String path;

	@Schema(description = "组件路径")
	@TableField("component")
	private String component;

	@Schema(description = "路由参数")
	@TableField("query")
	private String query;

	@Schema(description = "是否为外链（0是 1否）")
	@TableField("is_frame")
	private Boolean frame;

	@Schema(description = "是否缓存（0缓存 1不缓存）")
	@TableField("is_cache")
	private Boolean cache;

	@Schema(description = "菜单类型（M目录 C菜单 F按钮）")
	@TableField("menu_type")
	private String menuType;

	@Schema(description = "菜单是否可见（0显示 1隐藏）")
	@TableField("visible")
	private String visible;

	@Schema(description = "菜单状态（0正常 1停用）")
	@TableField("status")
	private String status;

	@Schema(description = "权限标识")
	@TableField("perms")
	private String perms;

	@Schema(description = "菜单图标")
	@TableField("icon")
	private String icon;

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

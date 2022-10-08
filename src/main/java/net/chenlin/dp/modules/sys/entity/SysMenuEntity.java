package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 菜单
 *
 * @author ZhouChenglin
 * @email fangyuan.co@outlook.com
 * @url www.chenlintech.com
 * @date 2017年8月9日 下午11:15:17
 */
@ApiModel
public class SysMenuEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "菜单id")
	private Long menuId;

	@ApiModelProperty(value = "父级id，一级菜单为0")
	private Long parentId;

	@ApiModelProperty(value = "父级菜单名称")
	private String parentName;

	@ApiModelProperty(value = "菜单名称")
	private String name;

	@ApiModelProperty(value = "菜单url")
	private String url;

	@ApiModelProperty(value = "授权标识(多个用逗号分隔，如：user:list,user:create)")
	private String perms;

	@ApiModelProperty(value = "类型(0：目录   1：菜单   2：按钮)")
	private Integer type;

	@ApiModelProperty(value = "菜单图标")
	private String icon;

	@ApiModelProperty(value = "排序")
	private Integer orderNum;

	@ApiModelProperty(value = "创建时间")
	private Timestamp gmtCreate;

	@ApiModelProperty(value = "修改时间")
	private Timestamp gmtModified;

	@ApiModelProperty(value = "ztree属性")
	private Boolean open;

	@ApiModelProperty(value = "")
	private List<?> list;

	public SysMenuEntity() {
		super();
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Timestamp gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

}

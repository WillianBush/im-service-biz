package net.chenlin.dp.modules.sys.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

/**
 * 角色与机构对应关系
 *
 * @author ZhouChenglin
 * @email fangyuan.co@outlook.com
 * @url www.chenlintech.com
 * @date 2017年8月17日 上午10:36:09
 */
@Getter
@ApiModel
public class SysRoleOrgEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录id
	 */
	@ApiModelProperty(value = "记录id")
	private Long id;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID")
	private Long roleId;

	/**
	 * 机构ID
	 */
	@ApiModelProperty(value = "机构ID")
	private Long orgId;

	public SysRoleOrgEntity() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}

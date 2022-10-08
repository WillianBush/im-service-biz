package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author ZhouChenglin
 * @email fangyuan.co@outlook.com
 * @url www.chenlintech.com
 * @date 2017年8月8日 下午2:42:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId;
	
	/**
	 * 机构id
	 */
	@ApiModelProperty(value = "机构id")

	private Long orgId;
	
	/**
	 * 机构名称
	 */
	@ApiModelProperty(value = "机构名称")
	private String orgName;
	
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;
	
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;
	
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;
	
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String mobile;
	
	/**
	 * 状态(0：禁用   1：正常)
	 */
	@ApiModelProperty(value = "状态 0：禁用   1：正常")
	private Integer status;
	
	/**
	 * 创建用户id
	 */
	@ApiModelProperty(value = "创建者id")
	private Long userIdCreate;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Timestamp gmtCreate;
	
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Timestamp gmtModified;
	
	/**
	 * 角色id列表
	 */
	@ApiModelProperty(value = "角色id列表")
	private List<Long> roleIdList;



	@ApiModelProperty(value = "角色列表")
	private List<SysRoleEntity> roleList;


}

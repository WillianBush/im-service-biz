package net.chenlin.dp.modules.biz.employee.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "employee")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class EmployeeEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "")
	@Column(name = "createDate")
	private Date createDate;
	
	@ApiModelProperty(value = "")
	@Column(name = "modifyDate")
	private Date modifyDate;
	
	@ApiModelProperty(value = "邀请码")
	@Column(name = "inviteCode")
	private String inviteCode;
	
	@ApiModelProperty(value = "")
	private String member_id;
	
	@ApiModelProperty(value = "")
	private String member_uuid;
	
	@ApiModelProperty(value = "昵称")
	private String name;

	@ApiModelProperty(value = "绑定白名单")
	private String ip_white;

	@ApiModelProperty(value = "站点")
	private Long orgId;

	@ApiModelProperty(value = "特权用户在线状态")
	private Long userStatus;

	@Transient
	private String nickName;

	@Transient
	private String username;

	@Transient
	private String lastLoginIp;
}

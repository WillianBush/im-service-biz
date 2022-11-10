package net.chenlin.dp.modules.biz.employeedefaultmessage.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 员工默认消息
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "employee_default_message")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class EmployeeDefaultMessageEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "员工 id")
	@Column(name = "employee_id")
	private String employee_id;
	
	@ApiModelProperty(value = "")
	@Column(name = "member_id")
	private String member_id;
	
	@ApiModelProperty(value = "打招呼话术")
	@Column(name = "msg_1")
	private String msg_1;
	
	@ApiModelProperty(value = "打招呼话术")
	@Column(name = "msg_2")
	private String msg_2;
	
	@ApiModelProperty(value = "打招呼话术")
	@Column(name = "msg_3")
	private String msg_3;
	
	@ApiModelProperty(value = "")
	@Column(name = "picture_1")
	private String picture_1;
	
	@ApiModelProperty(value = "")
	@Column(name = "picture_2")
	private String picture_2;
	
	@ApiModelProperty(value = "")
	@Column(name = "picture_3")
	private String picture_3;
	
	@ApiModelProperty(value = "")
	@Column(name = "picture_4")
	private String picture_4;
	
	@ApiModelProperty(value = "")
	@Column(name = "picture_5")
	private String picture_5;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "邀请码")
	@Column(name = "invite_code")
	private String invite_code;

	@ApiModelProperty(value = "站点")
	@Column(name = "org_id")
	private String org_id;

	@Transient
	private String nickName;

	@Transient
	private String member_uuid;
	


}

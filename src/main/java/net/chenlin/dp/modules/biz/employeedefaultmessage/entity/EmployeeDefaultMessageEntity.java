package net.chenlin.dp.modules.biz.employeedefaultmessage.entity;

import lombok.*;

import javax.persistence.Table;
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
	private String employee_id;
	
	@ApiModelProperty(value = "")
	private String member_id;
	
	@ApiModelProperty(value = "打招呼话术")
	private String msg_1;
	
	@ApiModelProperty(value = "打招呼话术")
	private String msg_2;
	
	@ApiModelProperty(value = "打招呼话术")
	private String msg_3;
	
	@ApiModelProperty(value = "")
	private String picture_1;
	
	@ApiModelProperty(value = "")
	private String picture_2;
	
	@ApiModelProperty(value = "")
	private String picture_3;
	
	@ApiModelProperty(value = "")
	private String picture_4;
	
	@ApiModelProperty(value = "")
	private String picture_5;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "邀请码")
	private String invite_code;
	


}

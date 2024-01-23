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

	/**
	 * 员工 id
	 */
	private String employeeId;

	private String memberId;

	/**
	 * 打招呼话术
	 */
	private String msg1;

	/**
	 * 打招呼话术
	 */
	private String msg2;

	/**
	 * 打招呼话术
	 */
	private String msg3;

	private String picture1;

	private String picture2;

	private String picture3;

	private String picture4;

	private String picture5;

	private String id;

	/**
	 * 邀请码
	 */
	private String inviteCode;

	private Long orgId;
}

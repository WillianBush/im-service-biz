package net.chenlin.dp.modules.biz.employee.entity;

import lombok.*;

import javax.persistence.Table;
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
	private Date createDate;
	
	@ApiModelProperty(value = "")
	private Date modifyDate;
	
	@ApiModelProperty(value = "")
	private String inviteCode;
	
	@ApiModelProperty(value = "")
	private String member_id;
	
	@ApiModelProperty(value = "")
	private String member_uuid;
	
	@ApiModelProperty(value = "")
	private String name;
	


}

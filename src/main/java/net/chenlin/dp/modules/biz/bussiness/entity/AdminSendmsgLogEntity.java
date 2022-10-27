package net.chenlin.dp.modules.biz.bussiness.entity;

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
@Table(name = "admin_sendmsg_log")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class AdminSendmsgLogEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "发送管理员ID")
	private String sendAdminId;
	
	@ApiModelProperty(value = "发送内容")
	private String txt;
	
	@ApiModelProperty(value = "")
	private Date createDate;
	
	@ApiModelProperty(value = "接收者ID")
	private String receiverId;
	
	@ApiModelProperty(value = "接收者名称")
	private String receiverName;
	


}

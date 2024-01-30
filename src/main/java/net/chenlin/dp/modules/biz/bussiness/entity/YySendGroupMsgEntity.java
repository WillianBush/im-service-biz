package net.chenlin.dp.modules.biz.bussiness.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "yy_send_group_msg")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class YySendGroupMsgEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "发送账号")
	private String sendMemberIds;
	
	@ApiModelProperty(value = "消息类型：0-文字；1-图片")
	private String msgType;
	
	@ApiModelProperty(value = "文字消息")
	private String message;
	
	@ApiModelProperty(value = "图片")
	private String picture;
	
	@ApiModelProperty(value = "")
	private Date createTime;
	


}

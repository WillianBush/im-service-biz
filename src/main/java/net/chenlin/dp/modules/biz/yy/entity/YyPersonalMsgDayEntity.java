package net.chenlin.dp.modules.biz.yy.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 运营-每次私发消息统计
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "yy_personal_msg_day")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class YyPersonalMsgDayEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "每日消息总数")
	private Double personal_message_total;
	
	@ApiModelProperty(value = "")
	private Date create_time;
	


}
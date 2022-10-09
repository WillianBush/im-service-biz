package net.chenlin.dp.modules.biz.site.entity;

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
@Table(name = "default_friend")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class DefaultFriendEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "好友ID")
	private String friend_id;
	
	@ApiModelProperty(value = "备注")
	private String note;
	
	@ApiModelProperty(value = "打招呼内容")
	private String send_hello_msg;
	


}

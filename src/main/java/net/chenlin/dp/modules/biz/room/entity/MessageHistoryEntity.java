package net.chenlin.dp.modules.biz.room.entity;

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
@Table(name = "message_history")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class MessageHistoryEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "")
	private String uuid;
	
	@ApiModelProperty(value = "")
	private String date;
	
	@ApiModelProperty(value = "发送者头像")
	private String fromHeadpic;
	
	@ApiModelProperty(value = "加工过的消息，如果是媒体消息，则是不带域名的上传地址")
	private String oldTxt;
	
	@ApiModelProperty(value = "接收对象的ID")
	private String toUid;

	@ApiModelProperty(value = "接收群组的ID")
	private String toGroupid;
	
	@ApiModelProperty(value = "")
	private String chatId;
	
	@ApiModelProperty(value = "解析方式  默认使用rich-text   还有其它的选择uparse voice(语音) video(视频)")
	private String psr;
	
	@ApiModelProperty(value = "")
	private String txt;
	
	@ApiModelProperty(value = "")
	private String aite;
	
	@ApiModelProperty(value = "发送人ID")
	private String fromUid;
	
	@ApiModelProperty(value = "发送人名字")
	private String fromName;
	
	@ApiModelProperty(value = "如果是媒体消息，则是多媒体格式 simple_content: [图片]")
	private String simpleContent;
	
	@ApiModelProperty(value = "")
	private Date createDate;
	
	@ApiModelProperty(value = "")
	private Date modifyDate;

	@ApiModelProperty(value = "站点")
	private String orgId;


}

package net.chenlin.dp.modules.biz.notice.entity;

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
@Table(name = "notice")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class NoticeEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "")
	private Date createDate;
	
	@ApiModelProperty(value = "")
	private Date modifyDate;
	
	@ApiModelProperty(value = "")
	private String content;
	
	@ApiModelProperty(value = "")
	private Integer status;
	
	@ApiModelProperty(value = "")
	private String title;
	


}

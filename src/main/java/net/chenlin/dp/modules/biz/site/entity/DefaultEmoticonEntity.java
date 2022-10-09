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
@Table(name = "default_emoticon")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class DefaultEmoticonEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "表情地址")
	private String img_addres;
	
	@ApiModelProperty(value = "上传时间")
	private Date up_time;
	
	@ApiModelProperty(value = "显示顺序")
	private Integer sort;
	


}

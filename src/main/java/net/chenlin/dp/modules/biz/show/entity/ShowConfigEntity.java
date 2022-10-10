package net.chenlin.dp.modules.biz.show.entity;

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
@Table(name = "show_config")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class ShowConfigEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "站点ID")
	private String site_id;
	
	@ApiModelProperty(value = "联系方式")
	private String contact;
	
	@ApiModelProperty(value = "app启动图片配置")
	private String app_start_img;
	
	@ApiModelProperty(value = "图标")
	private String icon;
	
	@ApiModelProperty(value = "logo")
	private String logo;
	


}

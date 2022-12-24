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
@Table(name = "homepage")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class HomepageEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "名称")
	private String name;
	
	@ApiModelProperty(value = "图标")
	private String icon;
	
	@ApiModelProperty(value = "链接")
	private String link;

	@ApiModelProperty(value = "详情")
	private String info;
	
	@ApiModelProperty(value = "参数开关0否1是")
	private Integer paramter_open;
	
	@ApiModelProperty(value = "所用用户可见0，特权用户专享1")
	private Integer rule;
	
	@ApiModelProperty(value = "顺序")
	private Integer sort;

	private Long org_id;
	


}

package net.chenlin.dp.modules.biz.site.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.Transient;

import javax.persistence.Table;
import java.io.Serializable;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "homepage_user")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class HomepageUserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Integer id;
	
	@ApiModelProperty(value = "mid")
	private Integer member_id;
	
	@ApiModelProperty(value = "外链ID")
	private String out_link_id;

	@ApiModelProperty(value = "开关状态:0-关闭；1-开启")
	private Integer status;

	@Transient
	private String out_link_name;

	@Transient
	private String username;

}

package net.chenlin.dp.modules.biz.bussiness.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "yy_ip_list")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class YyIpListEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	
	@ApiModelProperty(value = "IP地址")
	private String ipAddress;
	
	@ApiModelProperty(value = "备注")
	private String note;
	
	@ApiModelProperty(value = "IP名单类型：0->ipweb白名单；1->ip黑名单")
	private String type;
	
	@ApiModelProperty(value = "状态：0-正常；1-删除")
	private String stauts;

	@ApiModelProperty(value = "站点")
	private Long orgId;

}

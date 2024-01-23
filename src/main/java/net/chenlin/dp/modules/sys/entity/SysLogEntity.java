package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 系统日志
 *
 * @author ZhouChenglin
 * @email fangyuan.co@outlook.com
 * @url www.chenlintech.com
 * @date 2017年8月14日 下午8:05:17
 */
@Data
@ApiModel
public class SysLogEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "日志id")
	private Long id;
	

	@ApiModelProperty(value = "操作用户id")
	private Long userId;
	

	@ApiModelProperty(value = "操作用户")
	private String username;
	

	@ApiModelProperty(value = "操作")
	private String operation;
	

	@ApiModelProperty(value = "方法")
	private String method;
	

	@ApiModelProperty(value = "参数")
	private String params;
	

	@ApiModelProperty(value = "耗时")
	private Long time;

	@ApiModelProperty(value = "操作ip地址")
	private String ip;

	@ApiModelProperty(value = "创建时间")
	private Timestamp gmtCreate;

	private Long orgId;

	public SysLogEntity() {
		super();
	}

	
}

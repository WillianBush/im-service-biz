package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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

	public SysLogEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
}

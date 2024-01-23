package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户Token
 *
 * @author ZhouChenglin
 * @email fangyuan.co@outlook.com
 * @url www.chenlintech.com
 * @date 2017年9月3日 上午3:27:06
 */
@Getter
@ApiModel
public class SysUserTokenEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId;
	
	/**
	 * token
	 */
	@ApiModelProperty(value = "token")
	private String token;
	
	/**
	 * 过期时间
	 */
	@ApiModelProperty(value = "过期时间")
	private Date gmtExpire;
	
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date gmtModified;

	public SysUserTokenEntity() {
		super();
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setGmtExpire(Date gmtExpire) {
		this.gmtExpire = gmtExpire;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

}

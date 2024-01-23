package net.chenlin.dp.modules.biz.site.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "function_config")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class FunctionConfigEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "站点ID")
	private String orgId;
	
	@ApiModelProperty(value = "禁止网页/客户端注册 0否1是")
	private Integer  webAppRegist;
	
	@ApiModelProperty(value = "网页/客户端显示IP 0否1是")
	private Integer  webAppIpShow;
	
	@ApiModelProperty(value = "单账号多段登录，网页/客户端最大登录数量")
	private Integer  maxLoginCounts;
	
	@ApiModelProperty(value = "安全登录开关0关1开")
	private Integer  safeLogin;
	
	@ApiModelProperty(value = "安全登录，最大登录重试次数")
	private Integer safeLoginRetryCounts;
	
	@ApiModelProperty(value = "安全登录，是否适用IP黑名单功能 0否1是")
	private Integer  safeLoginApplyBlacklist;
	
	@ApiModelProperty(value = "禁止普通用户添加好友0否1是")
	private Integer  addFriend;
	
	@ApiModelProperty(value = "禁止普通用户建群0否1是")
	private Integer  createRoom;
	
	@ApiModelProperty(value = "开启手机号验证0否1是")
	private Integer  phoneVerify;
	
	@ApiModelProperty(value = "群主/群管理踢人是否删除被踢人信息0否1是")
	private Integer  outRoomDelMessage;
	
	@ApiModelProperty(value = "隐藏群主/群管理的管理消息（禁言、撤回消息等）0否1是")
	private Integer  missRoomManagerMsg;

	@ApiModelProperty(value = "安全登陆：是否使用账号冻结功能 o:否;1:是")
	private Integer  isFrozen;

	@ApiModelProperty(value = "安全登陆：是否使用IP黑名单功能 o:否;1:是")
	private Integer  ipBlack;

	@ApiModelProperty(value = "安全登陆：是否使用IP Web白名单功能 0否1是")
	private Integer  ipWhite;

	@ApiModelProperty(value = "用户外链开关：限制用户访问外链总开关 0不限制 1限制")
	private Integer  outLinkStatus;
	


}

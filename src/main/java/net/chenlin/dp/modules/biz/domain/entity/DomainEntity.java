package net.chenlin.dp.modules.biz.domain.entity;

import lombok.*;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "domain")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 1: 服务器域名；2 短域名
	 */
	private Integer domainType;
	
	/**
	 * 域名
	 */
	private String domainName;
	
	/**
	 * 1:启用；2：禁用
	 */
	private Integer domainEnable;
	
	/**
	 * 是否被封， 1：正常 2：不可用
	 */
	private Integer isBlocked;
	
	/**
	 * 域名证书地址
	 */
	private String httpsTomcatJksAddress;
	
	/**
	 * 域名证书密码
	 */
	private String httpsTomcatPass;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 创建人
	 */
	private String createBy;
	
	/**
	 * 更新人
	 */
	private String updateBy;

	/**
	 * 服务器IP_id
	 * */

	private Integer serverId;

	/**
	 * 服务器IP
	 * */

	private String serverName;

	private Integer domainOrigin;


	@Transient
	private String appName;

	private Integer qqChecked;

}

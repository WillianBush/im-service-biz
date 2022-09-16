package net.chenlin.dp.modules.biz.appDomain.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Transient;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Data
@ToString
@Table(name = "app_domain")
public class AppDomainEntity implements Serializable {
	
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
	 * app原包名
	 */
	private String appBaseName;
	
	/**
	 * 安卓重签后的包名
	 */
	private String androidResignedPackageName;
	
	/**
	 * 安卓重签后的id
	 */
	private Long appResignedId;
	
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
	 * 1:启用；2：禁用
	 */
	@Transient
	private Integer domainEnable;

	@Transient
	private Integer isBlocked;

	@Transient
	private Integer domainOrigin;

	@Transient
	private Integer qqChecked;

}

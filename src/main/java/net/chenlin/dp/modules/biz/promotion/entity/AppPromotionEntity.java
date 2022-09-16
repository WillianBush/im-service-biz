package net.chenlin.dp.modules.biz.promotion.entity;

import lombok.*;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;



/**
 * 域名URL
 * @author wang<fangyuan.co@outlook.com>
 */

@Table(name = "app_promotion")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppPromotionEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 推广url
	 */
	private String promotionUrl;
	
	/**
	 * 重签后的AppID
	 */
	private Long appResignedD;
	
	/**
	 * AppID原包
	 */
	private Long appBaseId;
	
	/**
	 * app名字
	 */
	private String appName;
	
	/**
	 * 短域名
	 */
	private String promotionDomain;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 过期时间
	 */
	private Date expireTime;

	@Transient
	private Integer qqChecked;

	@Transient
	private Integer isBlocked;


}

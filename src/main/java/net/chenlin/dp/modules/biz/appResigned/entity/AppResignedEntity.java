package net.chenlin.dp.modules.biz.appResigned.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;



/**
 * 重签后的app信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "app_resigned")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppResignedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private String androidResignedDownloadAddress;
	
	/**
	 * 
	 */
	private String iosDownloadAddrss;
	
	/**
	 * 
	 */
	private Integer androidDownloadTimes;
	
	/**
	 * 
	 */
	private Integer iosDownloadTimes;
	
	/**
	 * 
	 */
	private Long appBaseId;
	
	/**
	 * 
	 */
	private String appBaseName;
	
	/**
	 * 
	 */
	private Date createTime;
	
	/**
	 * 
	 */
	private Date updateTime;
	
	/**
	 * 安卓重签后的报名
	 */
	private String androidResignedPackageName;
	
	/**
	 * 
	 */
	private String createBy;
	
	/**
	 * 
	 */
	private String updateBy;

	@Transient
	private Long shortLinkCounts;

	@Transient
	private Long normalLinkCounts;

}

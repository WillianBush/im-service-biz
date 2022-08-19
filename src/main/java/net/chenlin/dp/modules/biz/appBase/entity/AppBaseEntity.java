package net.chenlin.dp.modules.biz.appBase.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * app基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "app_base")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class AppBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * app名字
	 */
	private String appName;
	
	/**
	 * 安卓原包地址
	 */
	private String appAndroidOriginAddress;
	
	/**
	 * ios原包地址
	 */
	private String appIosOriginAddrss;
	
	/**
	 * 安卓原始包名
	 */
	private String appAndroidOriginPackageName;
	
	/**
	 * ios包名
	 */
	private String appIosOriginPackageName;
	
	/**
	 * 安卓证书名

	 */
	private String appAndroidKeystoreName;
	
	/**
	 * 安卓证书密码
	 */
	private String appAndroidKeystorePassword;
	
	/**
	 * 安卓证书地址
	 */
	private String appAndroidKeystoreAddress;
	
	/**
	 * 
	 */
	private String appHomePageServerIds;
	
	/**
	 * 
	 */
	private Date createTime;
	
	/**
	 * 
	 */
	private Date updateTime;
	
	/**
	 * 
	 */
	private String createBy;
	
	/**
	 * 
	 */
	private String updateBy;
	


}

package net.chenlin.dp.modules.biz.appResigned.entity;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 重签后的app信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "app_resigned")
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
	
    /**
     * AppResignedEntity constructor
     */
	public AppResignedEntity() {
		super();
	}

    /**
     * setter for id
     * @param id
     */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * getter for id
     */
	public Long getId() {
		return id;
	}

    /**
     * setter for androidResignedDownloadAddress
     * @param androidResignedDownloadAddress
     */
	public void setAndroidResignedDownloadAddress(String androidResignedDownloadAddress) {
		this.androidResignedDownloadAddress = androidResignedDownloadAddress;
	}

    /**
     * getter for androidResignedDownloadAddress
     */
	public String getAndroidResignedDownloadAddress() {
		return androidResignedDownloadAddress;
	}

    /**
     * setter for iosDownloadAddrss
     * @param iosDownloadAddrss
     */
	public void setIosDownloadAddrss(String iosDownloadAddrss) {
		this.iosDownloadAddrss = iosDownloadAddrss;
	}

    /**
     * getter for iosDownloadAddrss
     */
	public String getIosDownloadAddrss() {
		return iosDownloadAddrss;
	}

    /**
     * setter for androidDownloadTimes
     * @param androidDownloadTimes
     */
	public void setAndroidDownloadTimes(Integer androidDownloadTimes) {
		this.androidDownloadTimes = androidDownloadTimes;
	}

    /**
     * getter for androidDownloadTimes
     */
	public Integer getAndroidDownloadTimes() {
		return androidDownloadTimes;
	}

    /**
     * setter for iosDownloadTimes
     * @param iosDownloadTimes
     */
	public void setIosDownloadTimes(Integer iosDownloadTimes) {
		this.iosDownloadTimes = iosDownloadTimes;
	}

    /**
     * getter for iosDownloadTimes
     */
	public Integer getIosDownloadTimes() {
		return iosDownloadTimes;
	}

    /**
     * setter for appBaseId
     * @param appBaseId
     */
	public void setAppBaseId(Long appBaseId) {
		this.appBaseId = appBaseId;
	}

    /**
     * getter for appBaseId
     */
	public Long getAppBaseId() {
		return appBaseId;
	}

    /**
     * setter for appBaseName
     * @param appBaseName
     */
	public void setAppBaseName(String appBaseName) {
		this.appBaseName = appBaseName;
	}

    /**
     * getter for appBaseName
     */
	public String getAppBaseName() {
		return appBaseName;
	}

    /**
     * setter for createTime
     * @param createTime
     */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    /**
     * getter for createTime
     */
	public Date getCreateTime() {
		return createTime;
	}

    /**
     * setter for updateTime
     * @param updateTime
     */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    /**
     * getter for updateTime
     */
	public Date getUpdateTime() {
		return updateTime;
	}

    /**
     * setter for androidResignedPackageName
     * @param androidResignedPackageName
     */
	public void setAndroidResignedPackageName(String androidResignedPackageName) {
		this.androidResignedPackageName = androidResignedPackageName;
	}

    /**
     * getter for androidResignedPackageName
     */
	public String getAndroidResignedPackageName() {
		return androidResignedPackageName;
	}

    /**
     * setter for createBy
     * @param createBy
     */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

    /**
     * getter for createBy
     */
	public String getCreateBy() {
		return createBy;
	}

    /**
     * setter for updateBy
     * @param updateBy
     */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

    /**
     * getter for updateBy
     */
	public String getUpdateBy() {
		return updateBy;
	}

    /**
     * AppResignedEntity.toString()
     */
    @Override
    public String toString() {
        return "AppResignedEntity{" +
               "id='" + id + '\'' +
               ", androidResignedDownloadAddress='" + androidResignedDownloadAddress + '\'' +
               ", iosDownloadAddrss='" + iosDownloadAddrss + '\'' +
               ", androidDownloadTimes='" + androidDownloadTimes + '\'' +
               ", iosDownloadTimes='" + iosDownloadTimes + '\'' +
               ", appBaseId='" + appBaseId + '\'' +
               ", appBaseName='" + appBaseName + '\'' +
               ", createTime='" + createTime + '\'' +
               ", updateTime='" + updateTime + '\'' +
               ", androidResignedPackageName='" + androidResignedPackageName + '\'' +
               ", createBy='" + createBy + '\'' +
               ", updateBy='" + updateBy + '\'' +
               '}';
    }

}

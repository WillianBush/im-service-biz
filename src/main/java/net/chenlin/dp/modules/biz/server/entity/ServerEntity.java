package net.chenlin.dp.modules.biz.server.entity;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 服务器基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "server")
public class ServerEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * SSH账号
	 */
	private String sshAccountName;
	
	/**
	 * SSH密码
	 */
	private String sshAccountPassword;
	
	/**
	 * 证书类型:  1:密码 , 2证书
	 */
	private Integer sshAccountType;
	
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
	 * 服务器IP
	 */
	private String serverIp;
	
	/**
	 * 服务器名
	 */
	private String serverName;
	
    /**
     * ServerEntity constructor
     */
	public ServerEntity() {
		super();
	}

    /**
     * setter for id
     * @param id
     */
	public void setId(Integer id) {
		this.id = id;
	}

    /**
     * getter for id
     */
	public Integer getId() {
		return id;
	}

    /**
     * setter for sshAccountName
     * @param sshAccountName
     */
	public void setSshAccountName(String sshAccountName) {
		this.sshAccountName = sshAccountName;
	}

    /**
     * getter for sshAccountName
     */
	public String getSshAccountName() {
		return sshAccountName;
	}

    /**
     * setter for sshAccountPassword
     * @param sshAccountPassword
     */
	public void setSshAccountPassword(String sshAccountPassword) {
		this.sshAccountPassword = sshAccountPassword;
	}

    /**
     * getter for sshAccountPassword
     */
	public String getSshAccountPassword() {
		return sshAccountPassword;
	}

    /**
     * setter for sshAccountType
     * @param sshAccountType
     */
	public void setSshAccountType(Integer sshAccountType) {
		this.sshAccountType = sshAccountType;
	}

    /**
     * getter for sshAccountType
     */
	public Integer getSshAccountType() {
		return sshAccountType;
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
     * setter for serverIp
     * @param serverIp
     */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

    /**
     * getter for serverIp
     */
	public String getServerIp() {
		return serverIp;
	}

    /**
     * setter for serverName
     * @param serverName
     */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

    /**
     * getter for serverName
     */
	public String getServerName() {
		return serverName;
	}

    /**
     * ServerEntity.toString()
     */
    @Override
    public String toString() {
        return "ServerEntity{" +
               "id='" + id + '\'' +
               ", sshAccountName='" + sshAccountName + '\'' +
               ", sshAccountPassword='" + sshAccountPassword + '\'' +
               ", sshAccountType='" + sshAccountType + '\'' +
               ", createTime='" + createTime + '\'' +
               ", updateTime='" + updateTime + '\'' +
               ", createBy='" + createBy + '\'' +
               ", updateBy='" + updateBy + '\'' +
               ", serverIp='" + serverIp + '\'' +
               ", serverName='" + serverName + '\'' +
               '}';
    }

}

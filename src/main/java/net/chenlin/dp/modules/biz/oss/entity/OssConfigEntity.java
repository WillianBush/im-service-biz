package net.chenlin.dp.modules.biz.oss.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "oss_config")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OssConfigEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 创建人
	 */
	private String createBy;
	
	/**
	 * 更新人
	 */
	private String updateBy;
	
	/**
	 * 
	 */
	private String endpoint;
	
	/**
	 * 
	 */
	private String apppoint;
	
	/**
	 * 
	 */
	private String accessKeyId;
	
	/**
	 * 
	 */
	private String bucketname;
	
	/**
	 * 
	 */
	private String ossAccelerateDomain;
	
	/**
	 * 
	 */
	private String accessKeySecret;
	
	/**
	 * 1:启用;2 关闭
	 */
	private Integer enabled;
	
	/**
	 * 
	 */
	private String ossGroupName;
	


}

package net.chenlin.dp.modules.biz.member.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "member_login_log")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberloginlogEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private Date createDate;
	
	/**
	 * 
	 */
	private Date modifyDate;
	
	/**
	 * 
	 */
	private String ip;
	
	/**
	 * 
	 */
	private String mid;
	
	/**
	 * 
	 */
	private String mnickName;
	
	/**
	 * 
	 */
	private String mtel;

	/**
	* 用户终端类型
	* */
	private String deviceType;

	/**
	 * 用户头像
	 * */
	private String mheadPic;

	/**
	 * 账号
	 * */
	private String username;

	/**
	 * 用户终端类型
	 * */
	private String deviceVersion;

	/**
	 *IP归属地
	 */
	private String ipAddr;

	/**
	 *站点
	 */
	private Long orgId;
}

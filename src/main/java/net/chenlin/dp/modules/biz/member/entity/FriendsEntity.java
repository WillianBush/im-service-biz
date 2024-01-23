package net.chenlin.dp.modules.biz.member.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "friends")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendsEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private Date createdate;
	
	/**
	 * 
	 */
	private Date modifydate;
	
	/**
	 * 
	 */
	private String friendid;
	
	/**
	 * 
	 */
	private String mid;

	/**
	 * 站点
	 */
	private int orgId;

	/**
	 * 备注
	 */
	private String note;

	private List<Member> members;


}

package net.chenlin.dp.modules.biz.room.entity;

import lombok.*;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "room")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomEntity implements Serializable {
	
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
	private String descri;
	
	/**
	 * 
	 */
	private String headimg;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	private Double openerprice;
	
	/**
	 * 
	 */
	private Double sendprice;
	
	/**
	 * 
	 */
	private Integer status;
	
	/**
	 * 
	 */
	private Integer controlgame;
	
	/**
	 * 
	 */
	private Integer controlmodel;
	
	/**
	 * 
	 */
	private Integer gametype;
	
	/**
	 * 
	 */
	private Integer limitnum;
	
	/**
	 * 
	 */
	private Double openfeeadd;
	
	/**
	 * 
	 */
	private Double openfeesum;
	
	/**
	 * 
	 */
	private String owner;
	
	/**
	 * 
	 */
	private Double playerwinrate_S1;
	
	/**
	 * 
	 */
	private String psw;
	
	/**
	 * 
	 */
	private Integer sendcount;
	
	/**
	 * 
	 */
	private Double sendfeeadd;
	
	/**
	 * 
	 */
	private Double sendfeesum;
	
	/**
	 * 
	 */
	private String undead;
	
	/**
	 * 
	 */
	private Double playeropenwinrate_S1;
	
	/**
	 * 
	 */
	private Double playersendwinrate_S1;
	
	/**
	 * 
	 */
	private String roomid;
	
	/**
	 * 
	 */
	private String props;
	
	/**
	 * 
	 */
	private String subname;
	
	/**
	 * 
	 */
	private String fixedrobotids;
	
	/**
	 * 
	 */
	private Double winfeeadd;
	
	/**
	 * 
	 */
	private Double winfeesum;
	
	/**
	 * 
	 */
	private String rulepic;
	
	/**
	 * 
	 */
	private Integer gamestatus;
	
	/**
	 * 
	 */
	private Date enddate;
	
	/**
	 * 
	 */
	private String owner_Uuid;

	private String ownerName;

	/**
	 * 
	 */
	private Integer independence;
	
	/**
	 * 
	 */
	private String agentredfandian;
	
	/**
	 * 
	 */
	private Integer maxrobotcount;
	
	/**
	 * 
	 */
	private Integer openreddelayend;
	
	/**
	 * 
	 */
	private Integer openreddelaystart;
	
	/**
	 * 
	 */
	private String fateqq_Codepay_Id;
	
	/**
	 * 
	 */
	private String fateqq_Key;
	
	/**
	 * 
	 */
	private String fateqq_Token;
	
	/**
	 * 
	 */
	private String domain;
	
	/**
	 * 
	 */
	private Integer robotrule;
	
	/**
	 * 
	 */
	private String member_Ids;
	
	/**
	 * 
	 */
	private String stopspeak_Member_Ids;
	
	/**
	 * 
	 */
	private String membermgr_Ids;
	
	/**
	 * 
	 */
	private Integer yaoqingable;
	
	/**
	 * 
	 */
	private Integer yaoqingauditable;
	
	/**
	 * 
	 */
	private Integer usecustomheadpic;

	private List<MemberEntity> members;

}

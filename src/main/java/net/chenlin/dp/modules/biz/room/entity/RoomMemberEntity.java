package net.chenlin.dp.modules.biz.room.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "room_member")
@Data
@ToString
@AllArgsConstructor
@Builder
public class RoomMemberEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 群组ID,主键
	 */
	private String roomId;

	/**
	 * 用户ID主键
	 */
	private String memberId;

	/**
	 * 是否管理员：0否，1是
	 */
	private Integer isManager;

	/**
	 * 是否禁言：0:否;1:是
	 */
	private Integer isStopSpeaker;

	private Date createDate;

	private Date modifyDate;

	private Integer orgId;

	public RoomMemberEntity() {
	}

	public RoomMemberEntity(String id,String  roomId, String  memberId) {
		this. roomId =  roomId;
		this. memberId =  memberId;
		/***设置默认值*/
		this. isManager=0;
		this. isStopSpeaker=0;
	}

	public RoomMemberEntity(String  roomId, String  memberId, Integer  isManager) {
		this. roomId =  roomId;
		this. memberId =  memberId;
		this. isManager =  isManager;
	}

	public RoomMemberEntity(String id, String roomId, String memberId, Integer isManager, Integer isStopSpeaker) {
		this.id = id;
		this.roomId = roomId;
		this.memberId = memberId;
		this.isManager = isManager;
		this.isStopSpeaker = isStopSpeaker;
	}
}

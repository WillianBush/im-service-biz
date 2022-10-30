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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomMemberEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 群组ID
	 */
	private String room_id;
	
	/**
	 * 用户ID
	 */
	private String member_id;
	
	/**
	 * 是否管理员：0否，1是
	 */
	private Integer is_manager;

	/**
	 * 创建日期
	 */
	private Date createDate;

	/**
	 * 修改日期
	 */
	private Date modifyDate;

	/**
	 * 是否管理员：0否，1是
	 */
	private Integer is_stop_speaker;

	public RoomMemberEntity(String id, String room_id, String member_id, Integer is_manager, Integer is_stop_speaker) {
		this.id = id;
		this.room_id = room_id;
		this.member_id = member_id;
		this.is_manager = is_manager;
		this.is_stop_speaker = is_stop_speaker;
	}

}

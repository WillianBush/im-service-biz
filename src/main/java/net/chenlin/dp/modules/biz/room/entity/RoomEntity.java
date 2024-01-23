package net.chenlin.dp.modules.biz.room.entity;

import lombok.*;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;

import javax.persistence.Column;
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
	private String id;
	@Column(name = "create_date")
	private Date createdate;
	@Column(name = "modify_date")
	private Date modifydate;
	private String descri;
	@Column(name = "head_img")
	private String headimg;
	private String name;
	@Column(name = "openner_price")
	private Double openerprice;
	@Column(name = "send_price")
	private Double sendprice;
	private Integer status;
	@Column(name = "control_game")
	private Integer controlgame;
	@Column(name = "control_model")
	private Integer controlmodel;
	@Column(name = "game_type")
	private Integer gametype;
	@Column(name = "limit_num")
	private Integer limitnum;
	@Column(name = "open_fee_add")
	private Double openfeeadd;
	@Column(name = "open_fee_sum")
	private Double openfeesum;
	private String owner;
	@Column(name = "player_win_rate_s1")
	private Double playerwinrate_S1;
	private String psw;
	private Integer sendcount;
	@Column(name = "send_fee_add")
	private Double sendfeeadd;
	@Column(name = "send_fee_sum")
	private Double sendfeesum;
	@Column(name = "un_dead")
	private String undead;
	@Column(name = "player_open_win_rate_s1")
	private Double playeropenwinrate_S1;
	@Column(name = "player_send_win_rate_s1")
	private Double playersendwinrate_S1;
	@Column(name = "room_id")
	private String roomid;
	private String props;
	private String subname;
	@Column(name = "fixed_robot_ids")
	private String fixedrobotids;
	@Column(name = "win_fee_add")
	private Double winfeeadd;
	@Column(name = "win_fee_sum")
	private Double winfeesum;
	@Column(name = "rule_pic")
	private String rulepic;
	@Column(name = "game_status")
	private Integer gamestatus;
	@Column(name = "end_date")
	private Date enddate;
	@Column(name = "owner_uuid")
	private String owner_Uuid;
//	private String ownerName;
	private Integer independence;
	@Column(name = "fixed_robot_ids")
	private String agentredfandian;
	@Column(name = "max_robot_count")
	private Integer maxrobotcount;
	@Column(name = "open_red_delay_end")
	private Integer openreddelayend;
	@Column(name = "open_red_delay_start")
	private Integer openreddelaystart;
	@Column(name = "fateqq_codepay_id")
	private String fateqq_Codepay_Id;
	@Column(name = "fateqq_key")
	private String fateqq_Key;
	@Column(name = "fateqq_token")
	private String fateqq_Token;
	private String domain;
	@Column(name = "robot_rule")
	private Integer robotrule;
	@Column(name = "member_ids")
	private String member_Ids;
	@Column(name = "stopspeak_member_ids")
	private String stopspeak_Member_Ids;
	@Column(name = "member_mgr_ids")
	private String membermgr_Ids;
	@Column(name = "yaoqing_able")
	private Integer yaoqingable;
	@Column(name = "yaoqing_audit_able")
	private Integer yaoqingauditable;
	@Column(name = "use_custom_headpic")
	private Integer usecustomheadpic;
	private List<MemberEntity> members;
	@Column(name = "org_id")
	private Long orgId;

}

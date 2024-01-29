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
	private Date createDate;
	@Column(name = "modify_date")
	private Date modifyDate;
	private String descri;
	@Column(name = "head_img")
	private String headImg;
	private String name;
	@Column(name = "openner_price")
	private Double openerPrice;
	@Column(name = "send_price")
	private Double sendPrice;
	private Integer status;
	@Column(name = "control_game")
	private Integer controlGame;
	@Column(name = "control_model")
	private Integer controlModel;
	@Column(name = "game_type")
	private Integer gameType;
	@Column(name = "limit_num")
	private Integer limitNum;
	@Column(name = "open_fee_add")
	private Double openFeeAdd;
	@Column(name = "open_fee_sum")
	private Double openFeeSum;
	private String owner;
	@Column(name = "player_win_rate_s1")
	private Double playerWinRateS1;
	private String psw;
	private Integer sendCount;
	@Column(name = "send_fee_add")
	private Double sendFeeAdd;
	@Column(name = "send_fee_sum")
	private Double sendFeeSum;
	@Column(name = "un_dead")
	private String unDead;
	@Column(name = "player_open_win_rate_s1")
	private Double playerOpenWinRateS1;
	@Column(name = "player_send_win_rate_s1")
	private Double playerSendWinRateS1;
	@Column(name = "room_id")
	private String roomId;
	private String props;
	private String subname;
	@Column(name = "fixed_robot_ids")
	private String fixedRobotIds;
	@Column(name = "win_fee_add")
	private Double winFeeAdd;
	@Column(name = "win_fee_sum")
	private Double winFeeSum;
	@Column(name = "rule_pic")
	private String rulePic;
	@Column(name = "game_status")
	private Integer gameStatus;
	@Column(name = "end_date")
	private Date endDate;
	@Column(name = "owner_uuid")
	private String ownerUuid;
//	private String ownerName;
	private Integer independence;
	@Column(name = "agent_red_fan_dian")
	private String agentRedFanDian;
	@Column(name = "max_robot_count")
	private Integer maxRobotCount;
	@Column(name = "open_red_delay_end")
	private Integer openRedDelayEnd;
	@Column(name = "open_red_delay_start")
	private Integer openRedDelayStart;
	@Column(name = "fateqq_codepay_id")
	private String fateqqCodepayId;
	@Column(name = "fateqq_key")
	private String fateqqKey;
	@Column(name = "fateqq_token")
	private String fateqqToken;
	private String domain;
	@Column(name = "robot_rule")
	private Integer robotRule;
	@Column(name = "member_ids")
	private String memberIds;
	@Column(name = "stopspeak_member_ids")
	private String stopspeakMemberIds;
	@Column(name = "member_mgr_ids")
	private String memberMgrIds;
	@Column(name = "yaoqing_able")
	private Integer yaoqingAble;
	@Column(name = "yaoqing_audit_able")
	private Integer yaoqingAuditAble;
	@Column(name = "use_custom_headpic")
	private Integer useCustomHeadpic;
	private List<MemberEntity> members;
	@Column(name = "org_id")
	private Long orgId;

}

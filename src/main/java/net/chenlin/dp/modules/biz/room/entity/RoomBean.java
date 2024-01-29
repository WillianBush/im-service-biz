package net.chenlin.dp.modules.biz.room.entity;


import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RoomBean {

	private String id;
	private String name;
	private String subName;
	private String roomId;
	private String roomUuid;
	private String img;
	private String descri;
	private String gameType;
	private Integer gameStatus=0;//0开机  1关机 【关机下所有不能抢包和发包】
	private String properties;//房间属性
	private Double sendFeeAdd=0.0;//发包服务费率
	private Double openFeeAdd=0.0;//抢包服务费率
	private Double winFeeAdd=0.0;//赢包服务费率【发包与抢包赢】
	
	private String unDead;//免死号，多个以,分开
	
	private Integer status=0;//0正常  1停止
	private String psw="";//房间密码。默认空
	private Integer controlGame=0;//启用游戏控制 1：是  0：否
	private Double playerSendWinRateS1;//【模式1】玩家发包赢率
	private Double playerOpenWinRateS1;//【模式1】玩家抢包赢率
	private String owner="";//房主ID，留空则平台为房主
	private String ownerUuid ="";//房主uuID，留空则平台为房主
	private String ownerName = "";
	private String ownerImg = "";
	private Integer limitNum=100;//人数限制
	private String fixedRobotIds;//禁抢玩家固定抢包机器人memberId集合，以,分开
	private String rulePic;//玩家规则图片
	
	private Date endDate;//到期时间  注：只适用于用户创建 的房间
//	put("STOPSPEAK","禁言#INTEGER#1");//0否 1是
	private Integer stopSpeak;//禁言
	private Integer independence=0;//是否为独立房间 0:否 1：是
	private String agentRedFanDian="";//红包代理返点
	
	private Integer robotEnable = 0;//机器人是否启用  0：否  1：是 【独立房间时使用】
	
	private Integer maxRobotCount = 0;//可用机器人最大数量【独立房间时使用】
	private Integer robotNum = 0;//机器人数量【独立房间时使用】
	private Integer openRedDelayStart = 0;//开包延迟最低秒数【独立房间时使用】
	private Integer openRedDelayEnd = 0;//开包延迟最高秒数【独立房间时使用】
	 
	private Double sendFeeSum=0.0;
	private Double winFeeSum=0.0;
	
	
	private String domain = "";
	private Integer robotRule=0;//0默认 1只抢不发
	
	private String memberIds;//成员ID集合  id#id#id#
	private List<String> top5Hp;//最前5个头像
	
	private String stopspeakMemberIds;//禁言成员ID集合  id#id#id#【针对单个用户禁言】
	private Integer yaoqingAble=0;//是否允许邀请 0否  1是  当否时只能管理员才邀请
	private Integer yaoqingAuditAble=0;//邀请是否需要核实 0否  1是  
	private String memberMgrIds = "";//群管理 ID集合  id#id#id#
	
	private Integer useCustomHeadpic = 0;//使用用户自定义图片 0否  1是
	private Integer orgId = 1;//站点

	
	
	public Integer getUseCustomHeadpic() {
		if(null==useCustomHeadpic) return 0;
		return useCustomHeadpic;
	}
	public void setUseCustomHeadpic(Integer useCustomHeadpic) {
		this.useCustomHeadpic = useCustomHeadpic;
	}
	
	public Integer getYaoqingAble() {
		return yaoqingAble;
	}
	public void setYaoqingAble(Integer yaoqingAble) {
		this.yaoqingAble = yaoqingAble;
	}
	public Integer getYaoqingAuditAble() {
		return yaoqingAuditAble;
	}
	public void setYaoqingAuditAble(Integer yaoqingAuditAble) {
		this.yaoqingAuditAble = yaoqingAuditAble;
	}
	public String getMemberMgrIds() {
		if(null== memberMgrIds) return "";
		return memberMgrIds;
	}
	public void setMemberMgrIds(String memberMgrIds) {
		this.memberMgrIds = memberMgrIds;
	}
	public String getStopspeakMemberIds() {
		if(null== stopspeakMemberIds) return "";
		return stopspeakMemberIds;
	}
	public void setStopspeakMemberIds(String stopspeakMemberIds) {
		this.stopspeakMemberIds = stopspeakMemberIds;
	}



	public String getOwnerImg() {
		return ownerImg;
	}


	public void setOwnerImg(String ownerImg) {
		this.ownerImg = ownerImg;
	}


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public List<String> getTop5Hp() {
		return top5Hp;
	}


	public void setTop5Hp(List<String> top5Hp) {
		this.top5Hp = top5Hp;
	}


	public Integer getMemberCount() {
		if(StringUtils.isEmpty(memberIds)) return 0;
		return memberIds.split("#").length;
	}
	
	
	public String getMemberIds() {
		return memberIds;
	}
	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}
	 
	public Integer getRobotRule() {
		return robotRule;
	}
	public void setRobotRule(Integer robotRule) {
		this.robotRule = robotRule;
	}
	public String getDomain() {
		if(null==domain) return "";
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
	public Double getSendFeeSum() {
		return sendFeeSum;
	}
	public void setSendFeeSum(Double sendFeeSum) {
		this.sendFeeSum = sendFeeSum;
	}
	public Double getWinFeeSum() {
		return winFeeSum;
	}
	public void setWinFeeSum(Double winFeeSum) {
		this.winFeeSum = winFeeSum;
	}
	public Integer getRobotNum() {
		if(null==robotNum) return 0;
		return robotNum;
	}
	public void setRobotNum(Integer robotNum) {
		this.robotNum = robotNum;
	}
	public Integer getMaxRobotCount() {
		if(null==maxRobotCount) return 0;
		return maxRobotCount;
	}
	public void setMaxRobotCount(Integer maxRobotCount) {
		this.maxRobotCount = maxRobotCount;
	}
	public Integer getOpenRedDelayStart() {
		if(null==openRedDelayStart) return 0;
		return openRedDelayStart;
	}
	public void setOpenRedDelayStart(Integer openRedDelayStart) {
		this.openRedDelayStart = openRedDelayStart;
	}
	public Integer getOpenRedDelayEnd() {
		if(null==openRedDelayEnd) return 0;
		return openRedDelayEnd;
	}
	public void setOpenRedDelayEnd(Integer openRedDelayEnd) {
		this.openRedDelayEnd = openRedDelayEnd;
	}
	
	public Integer getRobotEnable() {
		if(null==robotEnable) return 0;
		return robotEnable;
	}

	public void setRobotEnable(Integer robotEnable) {
		this.robotEnable = robotEnable;
	}

	public String getAgentRedFanDian() {
		if(null==agentRedFanDian) return "";
		return agentRedFanDian;
	}

	public void setAgentRedFanDian(String agentRedFanDian) {
		this.agentRedFanDian = agentRedFanDian;
	}
	
	public Integer getIndependence() {
		return independence;
	}

	public void setIndependence(Integer independence) {
		this.independence = independence;
	}

	public String getEndDateTime() {
		if(null==endDate) return "";
		return endDate.getTime()-new Date().getTime()+"";
	}
	
	public String getEndDateStr() {
		if(null==endDate) return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(endDate);
	}
	
	public Integer getStopSpeak() {
		if(null==stopSpeak) return 0;
		return stopSpeak;
	}
	public void setStopSpeak(Integer stopSpeak) {
		this.stopSpeak = stopSpeak;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getOwnerUuid() {
		if(null== ownerUuid) return "";
		return ownerUuid;
	}
	public void setOwnerUuid(String ownerUuid) {
		this.ownerUuid = ownerUuid;
	}
	public Integer getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(Integer gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String getRulePic() {
		return rulePic;
	}
	public void setRulePic(String rulePic) {
		this.rulePic = rulePic;
	}
	public Double getWinFeeAdd() {
		return winFeeAdd;
	}
	public void setWinFeeAdd(Double winFeeAdd) {
		this.winFeeAdd = winFeeAdd;
	}
	public String getFixedRobotIds() {
		return fixedRobotIds;
	}
	public void setFixedRobotIds(String fixedRobotIds) {
		this.fixedRobotIds = fixedRobotIds;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public Integer getControlGame() {
		return controlGame;
	}
	public void setControlGame(Integer controlGame) {
		this.controlGame = controlGame;
	}
	public Double getPlayerSendWinRateS1() {
		return playerSendWinRateS1;
	}
	public void setPlayerSendWinRateS1(Double playerSendWinRateS1) {
		this.playerSendWinRateS1 = playerSendWinRateS1;
	}
	public Double getPlayerOpenWinRateS1() {
		return playerOpenWinRateS1;
	}
	public void setPlayerOpenWinRateS1(Double playerOpenWinRateS1) {
		this.playerOpenWinRateS1 = playerOpenWinRateS1;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Integer getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	public String getUnDead() {
		if(null==unDead) return ""; 
		return unDead;
	}
	public void setUnDead(String unDead) {
		this.unDead = unDead;
	}
	public Double getSendFeeAdd() {
		return sendFeeAdd;
	}
	public void setSendFeeAdd(Double sendFeeAdd) {
		this.sendFeeAdd = sendFeeAdd;
	}
	public Double getOpenFeeAdd() {
		return openFeeAdd;
	}
	public void setOpenFeeAdd(Double openFeeAdd) {
		this.openFeeAdd = openFeeAdd;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getDescri1() {
		String str = descri.replaceAll("\n", "<br/>");
		return str;
	} 
	public String getDescri() { 
		return descri;
	}
	public void setDescri(String descri) {
		this.descri = descri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomUuid() {
		return roomUuid;
	}
	public void setRoomUuid(String roomUuid) {
		this.roomUuid = roomUuid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}

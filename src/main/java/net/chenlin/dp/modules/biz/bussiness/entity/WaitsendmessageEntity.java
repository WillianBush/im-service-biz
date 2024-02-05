package net.chenlin.dp.modules.biz.bussiness.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "wait_send_message")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class WaitsendmessageEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "")
	private Date createDate;
	
	@ApiModelProperty(value = "")
	private Date modifyDate;
	
	@ApiModelProperty(value = "")
	private String chatId;
	
	@ApiModelProperty(value = "")
	private String content;
	
	@ApiModelProperty(value = "")
	private String date;
	
	@ApiModelProperty(value = "")
	private String fromUid;
	
	@ApiModelProperty(value = "")
	private String headpic;
	
	@ApiModelProperty(value = "")
	private String name;
	
	@ApiModelProperty(value = "")
	private String toUid;
	
	@ApiModelProperty(value = "")
	private String type;
	
	@ApiModelProperty(value = "")
	private String psr;
	
	@ApiModelProperty(value = "")
	private String subTxt;
	
	@ApiModelProperty(value = "")
	private String oldContent;
	
	@ApiModelProperty(value = "")
	private String uuid;
	
	@ApiModelProperty(value = "")
	private String cmd;
	
	@ApiModelProperty(value = "")
	private String descri;
	
//	@ApiModelProperty(value = "")
//	private String descri1;
	
	@ApiModelProperty(value = "")
	private BigDecimal money;
	
	@ApiModelProperty(value = "")
	private Integer number;
	
	@ApiModelProperty(value = "")
	private Integer redType;
	
	@ApiModelProperty(value = "")
	private String redUUID;
	
	@ApiModelProperty(value = "")
	private BigDecimal sendMoney;
	
	@ApiModelProperty(value = "")
	private String toGroupid;
	
	@ApiModelProperty(value = "")
	private String simpleContent;
	
	@ApiModelProperty(value = "")
	private String utid;
	
	@ApiModelProperty(value = "")
	private String expiredTimeStr;
	
	@ApiModelProperty(value = "")
	private String mheadpic;
	
	@ApiModelProperty(value = "")
	private String mid;
	
	@ApiModelProperty(value = "")
	private String mname;
	
	@ApiModelProperty(value = "")
	private String muuid;
	


}

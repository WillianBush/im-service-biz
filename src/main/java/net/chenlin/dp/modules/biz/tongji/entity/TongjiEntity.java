package net.chenlin.dp.modules.biz.tongji.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "tongji")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class TongjiEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;
	
	@ApiModelProperty(value = "")
	private Date createDate;
	
	@ApiModelProperty(value = "")
	private Date modifyDate;
	
	@ApiModelProperty(value = "")
	private Double chongzhi_price;
	
	@ApiModelProperty(value = "")
	private Double todayOpenRedReward_price;
	
	@ApiModelProperty(value = "")
	private Integer todayRegisterCount;
	
	@ApiModelProperty(value = "")
	private Double todaySendRedReward_price;
	
	@ApiModelProperty(value = "")
	private Double todayService_price;
	
	@ApiModelProperty(value = "")
	private Double todayTichen_price;
	
	@ApiModelProperty(value = "")
	private Double tx_price;
	
	@ApiModelProperty(value = "")
	private Double undeadOpenRed_price;
	
	@ApiModelProperty(value = "")
	private Integer sendRed;
	
	@ApiModelProperty(value = "")
	private Double sendRedPrice;
	
	@ApiModelProperty(value = "")
	private Double todayChongzhi_price;
	
	@ApiModelProperty(value = "")
	private Double todayTransferPrice;
	
	@ApiModelProperty(value = "")
	private Double todayTx_price;
	
	@ApiModelProperty(value = "")
	private Double transferPrice;
	


}

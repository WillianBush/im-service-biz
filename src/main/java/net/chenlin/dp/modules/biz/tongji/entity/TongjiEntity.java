package net.chenlin.dp.modules.biz.tongji.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



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
public class TongjiEntity implements Serializable {
	
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
	private Double chongzhi_price;
	
	/**
	 * 
	 */
	private Double todayOpenRedReward_price;
	
	/**
	 * 
	 */
	private Integer todayRegisterCount;
	
	/**
	 * 
	 */
	private Double todaySendRedReward_price;
	
	/**
	 * 
	 */
	private Double todayService_price;
	
	/**
	 * 
	 */
	private Double todayTichen_price;
	
	/**
	 * 
	 */
	private Double tx_price;
	
	/**
	 * 
	 */
	private Double undeadOpenRed_price;
	
	/**
	 * 
	 */
	private Integer sendRed;
	
	/**
	 * 
	 */
	private Double sendRedPrice;
	
	/**
	 * 
	 */
	private Double todayChongzhi_price;
	
	/**
	 * 
	 */
	private Double todayTransferPrice;
	
	/**
	 * 
	 */
	private Double todayTx_price;
	
	/**
	 * 
	 */
	private Double transferPrice;
	


}

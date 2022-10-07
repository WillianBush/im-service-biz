package net.chenlin.dp.modules.biz.ipwhite.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "ipwhite")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IpwhiteEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private String ip_address;
	
	/**
	 * 
	 */
	private String ip_address_start;
	
	/**
	 * 
	 */
	private Integer status;
	
	/**
	 * 
	 */
	private String note;
	
	/**
	 * 
	 */
	private Integer type;
	


}

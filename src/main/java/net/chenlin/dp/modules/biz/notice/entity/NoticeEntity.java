package net.chenlin.dp.modules.biz.notice.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "notice")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeEntity implements Serializable {
	
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
	private String content;
	
	/**
	 * 
	 */
	private Integer status;
	
	/**
	 * 
	 */
	private String title;
	


}

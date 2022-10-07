package net.chenlin.dp.modules.biz.employee.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "employee")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private Date createdate;
	
	/**
	 * 
	 */
	private Date modifydate;
	
	/**
	 * 
	 */
	private String invitecode;
	
	/**
	 * 
	 */
	private String member_Id;
	
	/**
	 * 
	 */
	private String member_Uuid;
	
	/**
	 * 
	 */
	private String name;
	


}

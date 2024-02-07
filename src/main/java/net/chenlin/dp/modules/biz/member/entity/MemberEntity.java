package net.chenlin.dp.modules.biz.member.entity;

import lombok.*;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "member")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity implements Serializable {

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
	private String headpic;
	
	/**
	 * 
	 */
	private Date lastLoginDate;
	
	/**
	 * 
	 */
	private String lastLoginIp;
	
	/**
	 * 
	 */
	private Double money;
	
	/**
	 * 
	 */
	private String nickName;
	
	/**
	 * 
	 */
	private String parent;
	
	/**
	 * 
	 */
	private String parentPath;
	
	/**
	 * 
	 */
	private String password;
	
	/**
	 * 
	 */
	private String registIp;
	
	/**
	 * 
	 */
	private Integer status;
	
	/**
	 * 
	 */
	private String telephone;
	

	
	/**
	 * 
	 */
	private String memberId;

	
	/**
	 * 
	 */
	private String username;
	
	/**
	 * 
	 */
	private String qrCodeImg;
	
	/**
	 * 
	 */
	private Double tichenPriceSum;
	
	/**
	 * 
	 */
	private String preTxImg;
	
	/**
	 * 
	 */
	private Integer preTxPay;
	
	/**
	 * 
	 */
	private Integer memberType;
	
	/**
	 * 
	 */
	private String parentmid;
	
	/**
	 * 
	 */
	private Integer oneLevelCount;
	
	/**
	 * 
	 */
	private Double rechargePriceToday;
	
	/**
	 * 
	 */
	private Double tichenPriceToday;
	
	/**
	 * 
	 */
	private Double txPriceToday;
	
	/**
	 * 
	 */
	private String preBankAddr;
	
	/**
	 * 
	 */
	private String preBankBelonger;
	
	/**
	 * 
	 */
	private String preBankCode;
	
	/**
	 * 
	 */
	private String preBankName;
	
	/**
	 * 
	 */
	private String wxOpenId;
	
	/**
	 * 
	 */
	private String qqOpenId;
	
	/**
	 * 
	 */
	private String sex;
	
	/**
	 * 
	 */
	private String payPwd;
	
	/**
	 * 
	 */
	private Integer sendTransferCount;
	
	/**
	 * 
	 */
	private Double sendTransferPriceSum;
	
	/**
	 * 
	 */
	private Integer shimingStatus;
	
	/**
	 * 
	 */
	private Integer sysGenerate;

	/**
	 * 站点ID
	 */
	private Long orgId;


	/**
	 * 是否为特权
	 * */
	@Transient
	private Integer isEmployee;
}

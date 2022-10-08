package net.chenlin.dp.modules.biz.member.entity;

import lombok.*;

import javax.persistence.Table;
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
	private Date createdate;
	
	/**
	 * 
	 */
	private Date modifydate;
	
	/**
	 * 
	 */
	private String headpic;
	
	/**
	 * 
	 */
	private Date lastlogindate;
	
	/**
	 * 
	 */
	private String lastloginip;
	
	/**
	 * 
	 */
	private Double money;
	
	/**
	 * 
	 */
	private String nickname;
	
	/**
	 * 
	 */
	private String parent;
	
	/**
	 * 
	 */
	private String parentpath;
	
	/**
	 * 
	 */
	private String password;
	
	/**
	 * 
	 */
	private String registip;
	
	/**
	 * 
	 */
	private Integer status;
	
	/**
	 * 
	 */
	private String telphone;
	
	/**
	 * 
	 */
	private Double txmoney;
	
	/**
	 * 
	 */
	private Double txmoneying;
	
	/**
	 * 
	 */
	private String memberid;
	
	/**
	 * 
	 */
	private Double losepricesum;
	
	/**
	 * 
	 */
	private Integer openredcount;
	
	/**
	 * 
	 */
	private Double openredpricesum;
	
	/**
	 * 
	 */
	private Double rechargepricesum;
	
	/**
	 * 
	 */
	private Integer sendredcount;
	
	/**
	 * 
	 */
	private Double sendredpricesum;
	
	/**
	 * 
	 */
	private Double txpricesum;
	
	/**
	 * 
	 */
	private Double winpricesum;
	
	/**
	 * 
	 */
	private String username;
	
	/**
	 * 
	 */
	private String qrcodeimg;
	
	/**
	 * 
	 */
	private Double tichenpricesum;
	
	/**
	 * 
	 */
	private String pretximg;
	
	/**
	 * 
	 */
	private Integer pretxpay;
	
	/**
	 * 
	 */
	private Integer membertype;
	
	/**
	 * 
	 */
	private String parentmid;
	
	/**
	 * 
	 */
	private Integer one_Level_Count;
	
	/**
	 * 
	 */
	private Double rechargeprice_Today;
	
	/**
	 * 
	 */
	private Double tichenprice_Today;
	
	/**
	 * 
	 */
	private Double txprice_Today;
	
	/**
	 * 
	 */
	private String prebank_Addr;
	
	/**
	 * 
	 */
	private String prebank_Belonger;
	
	/**
	 * 
	 */
	private String prebank_Code;
	
	/**
	 * 
	 */
	private String prebank_Name;
	
	/**
	 * 
	 */
	private String wxopenid;
	
	/**
	 * 
	 */
	private String qqopenid;
	
	/**
	 * 
	 */
	private String sex;
	
	/**
	 * 
	 */
	private String pay_Pwd;
	
	/**
	 * 
	 */
	private Integer sendtransfercount;
	
	/**
	 * 
	 */
	private Double sendtransferpricesum;
	
	/**
	 * 
	 */
	private Integer shimingstatus;
	
	/**
	 * 
	 */
	private Integer sysgenerate;
	


}

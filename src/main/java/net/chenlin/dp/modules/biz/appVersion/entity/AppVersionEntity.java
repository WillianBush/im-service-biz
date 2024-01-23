package net.chenlin.dp.modules.biz.appVersion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * app版本升级
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "app_version")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class AppVersionEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
	
	@ApiModelProperty(value = "修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtModified;
	
	@ApiModelProperty(value = "版本号")
	private String version;
	
	@ApiModelProperty(value = "描述")
	private String descript;
	
	@ApiModelProperty(value = "站点(同orgId)")
	private Long orgId;
	
	@ApiModelProperty(value = "系统(1安卓2为IOS)")
	private String deviceType;
	
	@ApiModelProperty(value = "下载链接")
	private String downUrl;
	
	@ApiModelProperty(value = "是否强更(0否1是)")
	private Integer forceUpdate;
	
	@ApiModelProperty(value = "0: 不显示, 1显示")
	private String isShow;
	
	@ApiModelProperty(value = "应用id")
	private String appId;
	
	@ApiModelProperty(value = "APP昵称")
	private String appName;




}

package net.chenlin.dp.modules.biz.bussiness.entity;

import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 运营-每日注册数据
 * @author wang<fangyuan.co@outlook.com>
 */
@Table(name = "yy_m_register_day")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class YyMRegisterDayEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "每日注册数量")
	private Double m_register_number;
	
	@ApiModelProperty(value = "")
	private Date create_time;
	
	@ApiModelProperty(value = "")
	private String id;
	


}

package net.chenlin.dp.modules.biz.bussiness.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @author wang<fangyuan.co @ outlook.com>
 */
@Table(name = "commodity")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class CommodityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品分类")
    private String category;

    @ApiModelProperty(value = "单价")
    private Double prize;

    @ApiModelProperty(value = "库存")
    private String in_stock;

    @ApiModelProperty(value = "商品图片路径")
    private Long img;

    @ApiModelProperty(value = "创建时间")
    private Timestamp create_time;

    @ApiModelProperty(value = "修改时间")
    private Timestamp modify_time;

    @ApiModelProperty(value = "修改时间")
    private int org_id;

}

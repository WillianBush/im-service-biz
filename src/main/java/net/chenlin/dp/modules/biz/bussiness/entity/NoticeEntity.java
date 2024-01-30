package net.chenlin.dp.modules.biz.bussiness.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * @author wang<fangyuan.co @ outlook.com>
 */
@Table(name = "notice")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class NoticeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    @ApiModelProperty(value = "公告内容")
    private String content;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "")
    private Long orgId;

}
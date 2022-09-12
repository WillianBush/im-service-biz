package net.chenlin.dp.modules.biz.domain.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;


@Data
@ToString
public class DomainOutCsv {

    private Long id;

    /**
     * 1: 服务器域名；2 推广域名
     */
    private Integer domainType;

    /**
     * 域名
     */
    private String domainName;

    /**
     * 1:启用；2：禁用
     */
    private Integer domainEnable;

    /**
     * 是否被封， 1：正常 2：不可用
     */
    private Integer isBlocked;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    @Transient
    private String appName;


}

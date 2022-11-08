package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class Domians implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long domainId;

    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id")
    private Long orgId;

    /**
     * http域名
     */
    @ApiModelProperty(value = "http域名")
    private String httpDomain;


    /**
     * ws域名
     */
    @ApiModelProperty(value = "ws域名")
    private String wsDomain;

    /**
     * oss域名
     */
    @ApiModelProperty(value = "oss域名")
    private String ossDomain;
}

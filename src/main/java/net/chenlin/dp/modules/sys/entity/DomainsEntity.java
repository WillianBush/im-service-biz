package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "domains")
@Data
@Builder
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class DomainsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long domainId;

    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id")
    private Long orgId;

//    private String orgName;
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
    /**
     * oss域名
     */
    @ApiModelProperty(value = "oss域名")
    private String admin_domain;
}

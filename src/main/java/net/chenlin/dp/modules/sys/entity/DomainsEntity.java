package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long domain_id;

    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id")
    private Long org_id;

    /**
     * http域名
     */
    @ApiModelProperty(value = "http域名")
    private String http_domain;


    /**
     * ws域名
     */
    @ApiModelProperty(value = "ws域名")
    private String ws_domain;

    /**
     * oss域名
     */
    @ApiModelProperty(value = "oss域名")
    private String oss_domain;
}

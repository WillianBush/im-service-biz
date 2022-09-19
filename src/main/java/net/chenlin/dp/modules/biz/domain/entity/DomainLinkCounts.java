package net.chenlin.dp.modules.biz.domain.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DomainLinkCounts implements Serializable {
    private static final long serialVersionUID = -3360934906649785291L;

    private String domainName;

    private Integer shortLink;

    private String appBaseName;
}

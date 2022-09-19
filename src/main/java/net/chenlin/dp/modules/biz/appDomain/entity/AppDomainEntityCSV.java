package net.chenlin.dp.modules.biz.appDomain.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author wang<fangyuan.co @ outlook.com>
 */
@Data
@ToString
public class AppDomainEntityCSV implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 1: 服务器域名；2 短域名
     */
    private String domainType;

    /**
     * 域名
     */
    private String domainName;

    /**
     * app原包名
     */
    private String appBaseName;

    /**
     * 创建时间
     */
    private String createTime;

    private String shortLink;


}

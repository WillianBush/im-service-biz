package net.chenlin.dp.modules.biz.member.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LastLoginDateEntity implements Serializable {
    private static final long serialVersionUID = -8213726078985594297L;

    private String mnickName;

    private String username;

    private String startDate;

    private String endDate;

    private String ip;

    private String memberId;

    private Long orgId;

}

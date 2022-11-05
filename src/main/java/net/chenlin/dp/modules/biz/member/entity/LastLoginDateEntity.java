package net.chenlin.dp.modules.biz.member.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LastLoginDateEntity implements Serializable {
    private static final long serialVersionUID = -8213726078985594297L;

    private String mnickname;

    private String username;

    private String startdate;

    private String enddate;

    private String ip;

    private String memberId;

    private int org_id;

}

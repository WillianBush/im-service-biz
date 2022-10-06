package net.chenlin.dp.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysLoginEntity implements Serializable {

    private static final long serialVersionUID = -1200918747178917004L;

    private String username;
    private String password;
//    private String kaptcha;
}

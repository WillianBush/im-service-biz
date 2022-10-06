package net.chenlin.dp.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysLoginResp implements Serializable {

    private static final long serialVersionUID = -2996184486831144469L;

//    private List<SysMenuEntity> menuList;
    private SysUserEntity sysUserEntity;
    private String token;
}

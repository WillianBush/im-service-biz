package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel
public class SysLoginResp implements Serializable {

    private static final long serialVersionUID = -2996184486831144469L;

//    private List<SysMenuEntity> menuList;
    @ApiModelProperty(value = "用户信息")
    private SysUserEntity sysUserEntity;

    @ApiModelProperty(value = "token")
    private String token;
}

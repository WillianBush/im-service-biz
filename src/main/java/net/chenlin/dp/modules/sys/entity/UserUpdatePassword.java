package net.chenlin.dp.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class UserUpdatePassword implements Serializable {
    private static final long serialVersionUID = 1781878947297808272L;

    @ApiModelProperty(value = "原密码")
    private String pswd;

    @ApiModelProperty(value = "新密码")
    private  String newPswd;
}

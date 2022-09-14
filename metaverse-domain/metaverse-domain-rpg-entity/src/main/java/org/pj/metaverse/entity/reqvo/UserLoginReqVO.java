package org.pj.metaverse.entity.reqvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author pengjie
 * @date 17:22 2022/8/23
 **/
@Data
public class UserLoginReqVO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "登录账号", required = true)
    private String userName;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "登录密码", required = true)
    private String userPassword;

}

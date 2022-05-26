package org.pj.metaverse.user.entity.reqvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjie
 * @date 10:59 2022/5/9
 **/
@Data
@ApiModel( description = "web账号密码登录的请求类")
public class WebUserLoginReqVO {
    @ApiModelProperty(value = "手机号/邮箱/账号",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "验证码",required = true)
    private String code;
}

package org.pj.metaverse.user.entity.reqvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjie
 * @date 15:30 2022/5/17
 **/
@Data
public class RegisteredAccountReqVO {

    @ApiModelProperty(value = "类型 1:手机号注册 2:邮箱注册 3:账号注册")
    private Integer type;

    @ApiModelProperty(value = "手机号")
    private String loginPhone;

    @ApiModelProperty(value = "邮箱")
    private String loginEmail;

    @ApiModelProperty(value = "账号")
    private String loginName;

    @ApiModelProperty(value = "密码",required = true)
    private String loginPassword;

    public static Integer TYPE_PHONE = 1;
    public static Integer TYPE_EMAIL = 2;
    public static Integer TYPE_NAME = 3;
}

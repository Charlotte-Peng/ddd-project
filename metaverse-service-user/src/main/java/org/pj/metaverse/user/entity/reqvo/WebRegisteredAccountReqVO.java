package org.pj.metaverse.user.entity.reqvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjie
 * @date 15:30 2022/5/17
 **/
@Data
public class WebRegisteredAccountReqVO {
    @ApiModelProperty(value = "账号",required = true)
    private String loginName;

    @ApiModelProperty(value = "密码",required = true)
    private String loginPassword;

    @ApiModelProperty(value = "验证码",required = true)
    private String code;
}

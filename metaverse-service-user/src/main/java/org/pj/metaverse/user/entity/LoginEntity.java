package org.pj.metaverse.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.common.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author pengjie
 * @since 2022-05-08 14:05:12
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("login")
@ApiModel(value = "LoginEntity对象", description = "")
public class LoginEntity extends BaseEntity {

    @ApiModelProperty("用户id")
    @TableId(value = "user_id", type = IdType.INPUT)
    private String userId;

    @ApiModelProperty("登陆账号")
    @TableField("login_name")
    private String loginName;

    @ApiModelProperty("登陆密码")
    @TableField("login_password")
    private String loginPassword;

    @ApiModelProperty("绑定的邮箱")
    @TableField("login_email")
    private String loginEmail;

    @ApiModelProperty("微信openId")
    @TableField("wx_open_id")
    private String wxOpenId;

    @ApiModelProperty("qq openId")
    @TableField("qq_open_id")
    private String qqOpenId;

    @ApiModelProperty("绑定的手机号")
    @TableField("login_phone")
    private String loginPhone;


}

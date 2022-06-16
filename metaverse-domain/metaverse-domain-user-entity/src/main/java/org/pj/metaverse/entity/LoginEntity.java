package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.constant.ServiceNameConstant;
import org.pj.metaverse.entity.BaseEntity;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;

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
@Entity
@RedisHash(ServiceNameConstant.SERVICE_NAME_USER+":"+"LoginEntity")
public class LoginEntity extends BaseEntity {

    @Indexed
    @ApiModelProperty("用户id")
    private String userId;

    @Indexed
    @ApiModelProperty("登陆账号")
    @TableField("login_name")
    private String loginName;

    @Indexed
    @ApiModelProperty("登陆密码")
    @TableField("login_password")
    private String loginPassword;

    @Indexed
    @ApiModelProperty("绑定的邮箱")
    @TableField("login_email")
    private String loginEmail;

    @Indexed
    @ApiModelProperty("微信openId")
    @TableField("wx_open_id")
    private String wxOpenId;

    @Indexed
    @ApiModelProperty("qq openId")
    @TableField("qq_open_id")
    private String qqOpenId;

    @Indexed
    @ApiModelProperty("绑定的手机号")
    @TableField("login_phone")
    private String loginPhone;


}

package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.constant.ServiceNameConstant;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Data
@Accessors(chain = true)
@TableName("t_user")
@ApiModel(value = "TUserEntity对象", description = "用户信息表")
public class TUserEntity {

    @ApiModelProperty("用户id")
    @TableId(value = "user_id", type = IdType.NONE)
    private String userId;

    @ApiModelProperty("账号")
    private String userName;

    @ApiModelProperty("密码")
    private String userPassword;

    @ApiModelProperty("用户编号")
    private Integer userNo;

    @ApiModelProperty("用户昵称")
    private String userNickName;

    @ApiModelProperty(value = "头像")
    private String userAvatar;

    @ApiModelProperty("0:女 1:男 2:未知")
    private Integer sex;

    @ApiModelProperty("角色金币")
    private Long gold;

    @ApiModelProperty("角色代币")
    private Long money;

    @ApiModelProperty("最近登录时间")
    private LocalDateTime loginTime;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("加入时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;
}

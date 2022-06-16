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
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author pengjie
 * @since 2022-05-08 13:54:53
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "UserEntity对象", description = "")
@Entity
@RedisHash(ServiceNameConstant.SERVICE_NAME_USER+":"+"UserEntity")
public class UserEntity extends BaseEntity {

    @Indexed
    @ApiModelProperty("用户id")
    private String userId;

    @Indexed
    @ApiModelProperty("用户编号")
    @TableField("user_no")
    private Integer userNo;

    @Indexed
    @ApiModelProperty("用户昵称")
    @TableField("user_nick_name")
    private String userNickName;

    @Indexed
    @ApiModelProperty("用户头像")
    @TableField("user_avatar")
    private String userAvatar;

    @Indexed
    @ApiModelProperty("生日")
    @TableField("birthday")
    private LocalDate birthday;

    @Indexed
    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @Indexed
    @ApiModelProperty("0:未知 1:南 2:女")
    @TableField("sex")
    private Integer sex;

}

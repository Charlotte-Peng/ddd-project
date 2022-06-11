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
public class UserEntity extends BaseEntity {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户编号")
    @TableField("user_no")
    private Integer userNo;

    @ApiModelProperty("用户昵称")
    @TableField("user_nick_name")
    private String userNickName;

    @ApiModelProperty("用户头像")
    @TableField("user_avatar")
    private String userAvatar;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("0:未知 1:南 2:女")
    @TableField("sex")
    private Integer sex;

}

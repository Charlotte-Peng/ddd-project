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

/**
 * <p>
 * 
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_role")
@ApiModel(value = "UserRoleEntity对象", description = "")
public class UserRoleEntity {

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;


}

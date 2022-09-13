package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号的角色关联表
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_user_role")
@ApiModel(value = "TUserRoleEntity对象", description = "账号的角色关联表")
public class TUserRoleEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("角色详情id")
    private Integer userRoleId;


}

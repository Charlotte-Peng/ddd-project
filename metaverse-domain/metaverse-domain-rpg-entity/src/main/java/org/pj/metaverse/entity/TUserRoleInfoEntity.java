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
 * 用户角色详情
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_user_role_info")
@ApiModel(value = "TUserRoleInfoEntity对象", description = "用户角色详情")
public class TUserRoleInfoEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色类型 1:主角 2:战士 3:奶妈")
    private Integer type;

    @ApiModelProperty("对应角色的id")
    private Integer roleId;

    @ApiModelProperty("角色等级")
    private Integer level;

    @ApiModelProperty("突破等级")
    private Integer breakThrough;

    @ApiModelProperty("角色经验")
    private Long experience;

    @ApiModelProperty("下级升级所需经验值")
    private Long nextExperience;


}

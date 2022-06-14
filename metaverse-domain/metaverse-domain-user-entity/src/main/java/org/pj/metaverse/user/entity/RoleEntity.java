package org.pj.metaverse.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.common.entity.BaseEntity;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("role")
@ApiModel(value = "RoleEntity对象", description = "角色信息表")
public class RoleEntity extends BaseEntity {

    @ApiModelProperty(value = "角色名字",required = true)
    @TableField("`name`")
    private String name;

    @ApiModelProperty(value = "是否启用 0:启动 1:关闭",hidden = true)
    @TableField("`enable`")
    private Integer enable;

}

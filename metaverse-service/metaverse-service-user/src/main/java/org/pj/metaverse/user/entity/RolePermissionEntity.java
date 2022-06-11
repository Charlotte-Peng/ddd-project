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
@TableName("role_permission")
@ApiModel(value = "RolePermissionEntity对象", description = "")
public class RolePermissionEntity {

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("权限id")
    @TableField("permission_id")
    private Integer permissionId;

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Integer roleId;


}

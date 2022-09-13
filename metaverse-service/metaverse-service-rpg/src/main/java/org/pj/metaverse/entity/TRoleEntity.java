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
 * 角色信息表
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_role")
@ApiModel(value = "TRoleEntity对象", description = "角色信息表")
public class TRoleEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色说明")
    private String roleExplain;

    @ApiModelProperty("角色星级")
    private Integer roleStar;


}

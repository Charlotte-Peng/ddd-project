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
import org.pj.metaverse.common.constant.ServiceNameConstant;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity
@RedisHash(ServiceNameConstant.SERVICE_NAME_USER+":"+"UserRoleEntity")
public class UserRoleEntity {

    @Id
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Indexed
    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Integer roleId;

    @Indexed
    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;


}

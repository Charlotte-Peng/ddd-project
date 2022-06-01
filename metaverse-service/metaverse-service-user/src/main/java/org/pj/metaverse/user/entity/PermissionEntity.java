package org.pj.metaverse.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.common.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 权限接口表
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("permission")
@ApiModel(value = "PermissionEntity对象", description = "权限接口表")
public class PermissionEntity extends BaseEntity {

    @ApiModelProperty(value = "接口名称",required = true)
    @TableField("`name`")
    private String name;

    @ApiModelProperty("注释")
    @TableField("annotation")
    private String annotation;

    @ApiModelProperty(value = "接口地址",required = true)
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "请求方式",required = true)
    @TableField("request_mode")
    private String requestMode;

    @ApiModelProperty(value = "是否启用 0:启动 1:关闭",hidden = true)
    @TableField("`enable`")
    private Integer enable;
}

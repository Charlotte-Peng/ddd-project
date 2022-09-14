package org.pj.metaverse.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.constant.ServiceNameConstant;
import org.pj.metaverse.entity.BaseEntity;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;

/**
 * <p>
 * 权限接口表
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Data
@ApiModel(value = "PermissionEntity对象", description = "权限接口表")
public class PermissionVO extends BaseEntity {

    @ApiModelProperty(value = "接口名称",required = true)
    private String name;

    @ApiModelProperty("注释")
    private String annotation;

    @ApiModelProperty(value = "接口地址",required = true)
    private String url;

    @ApiModelProperty(value = "请求方式",required = true)
    private String requestMode;

    @ApiModelProperty(value = "是否启用 0:启动 1:关闭",hidden = true)
    private Integer enable;
}

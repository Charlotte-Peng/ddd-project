package org.pj.metaverse.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.common.constant.ServiceNameConstant;
import org.pj.metaverse.common.entity.BaseEntity;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author pengjie
 * @since 2022-05-09 11:26:30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("system_dictionary")
@ApiModel(value = "SystemDictionaryEntity对象", description = "系统字典实体")
@RedisHash(ServiceNameConstant.SERVICE_NAME_SYSTEM+":"+"SystemDictionaryEntity")
@Entity
public class SystemDictionaryEntity extends BaseEntity {

    @Indexed
    @ApiModelProperty("key")
    @TableField("`key`")
    private String key;

    @Indexed
    @ApiModelProperty("value")
    @TableField("`value`")
    private String value;

    @Indexed
    @ApiModelProperty("是否启用 0:启动 1:关闭")
    @TableField("`enable`")
    private Integer enable;

    public static Integer ENABLE_CONSTANT_ENABLE = 0;
    public static Integer ENABLE_CONSTANT_DISENABLE = 1;


}

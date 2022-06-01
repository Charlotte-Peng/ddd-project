package org.pj.metaverse.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
public class SystemDictionaryEntity {

    @ApiModelProperty("key")
    @TableField("`key`")
    private String key;

    @ApiModelProperty("value")
    @TableField("`value`")
    private String value;

    @ApiModelProperty("是否启用 0:启动 1:关闭")
    @TableField("`enable`")
    private Integer enable;

    @ApiModelProperty(value = "创建人",hidden = true)
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人",hidden = true)
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty(value = "修改时间",hidden = true)
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除",hidden = true)
    @TableField("deleted")
    @TableLogic
    private String deleted;

    public static Integer ENABLE_CONSTANT_ENABLE = 0;
    public static Integer ENABLE_CONSTANT_DISENABLE = 1;


}

package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 16:12 2022/8/25
 **/
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_dictionary")
@ApiModel(value = "TDictionary对象", description = "字典表信息")
public class TDictionaryEntity {

    @ApiModelProperty("字典key")
    @TableId(value = "`key`", type = IdType.INPUT)
    private String key;

    @ApiModelProperty("字典key")
    @TableField("`value`")
    private String value;

    @ApiModelProperty("注释")
    @TableField("`explain`")
    private String explain;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}

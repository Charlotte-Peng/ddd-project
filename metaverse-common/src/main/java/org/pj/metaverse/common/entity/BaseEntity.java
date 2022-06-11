package org.pj.metaverse.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 10:10 2022/5/26
 **/
@Data
@KeySequence(value = "SEQ_MYSQL_STRING_KEY",dbType = DbType.MYSQL)
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6557137272282292131L;

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "创建人",hidden = true)
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty(value = "创建时间",hidden = true)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人",hidden = true)
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty(value = "修改时间",hidden = true)
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除 Y/N",hidden = true)
    @TableField("deleted")
    @TableLogic
    private String deleted;

    @ApiModelProperty(value = "排序规则",hidden = true)
    @TableField(exist = false)
    private String orderBy;
}

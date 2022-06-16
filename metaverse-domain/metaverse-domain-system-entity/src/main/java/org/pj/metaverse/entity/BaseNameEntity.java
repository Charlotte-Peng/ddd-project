package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统通用昵称
 * </p>
 *
 * @author pengjie
 * @since 2022-05-17 16:36:21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("base_name")
@ApiModel(value = "BaseNameEntity对象", description = "系统通用昵称")
public class BaseNameEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("名字")
    @TableField("`name`")
    private String name;

    @ApiModelProperty("创建人")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改人")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("类型 1:用户昵称")
    @TableField("`type`")
    private Integer type;

    @ApiModelProperty("逻辑删除 Y/N")
    @TableField("deleted")
    @TableLogic
    private String deleted;

}

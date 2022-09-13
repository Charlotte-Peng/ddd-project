package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pj.metaverse.entity.BaseEntity;

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
@TableName("group")
@ApiModel(value = "GroupEntity对象", description = "")
public class GroupEntity extends BaseEntity {
    @ApiModelProperty("群组名称")
    @TableField("`name`")
    private String name;

    @ApiModelProperty("是否启用 0:启动 1:关闭")
    @TableField("`enable`")
    private Integer enable;
}

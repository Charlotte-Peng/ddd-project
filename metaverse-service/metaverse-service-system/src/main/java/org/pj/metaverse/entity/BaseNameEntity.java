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
public class BaseNameEntity extends BaseEntity {

    @ApiModelProperty("名字")
    @TableField("`name`")
    private String name;

    @ApiModelProperty("类型 1:用户昵称")
    @TableField("`type`")
    private Integer type;
}

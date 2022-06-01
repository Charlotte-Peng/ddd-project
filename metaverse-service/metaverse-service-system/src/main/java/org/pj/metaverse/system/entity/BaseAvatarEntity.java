package org.pj.metaverse.system.entity;

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
 * 系统通用头像
 * </p>
 *
 * @author pengjie
 * @since 2022-05-17 16:36:21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("base_avatar")
@ApiModel(value = "BaseAvatarEntity对象", description = "系统通用头像")
public class BaseAvatarEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("名字")
    @TableField("avatar")
    private String avatar;

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

    @ApiModelProperty("类型 1:用户头像")
    @TableField("`type`")
    private Integer type;

    @ApiModelProperty("逻辑删除 Y/N")
    @TableField("deleted")
    @TableLogic
    private String deleted;

}

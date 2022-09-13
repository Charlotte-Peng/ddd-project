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
public class BaseAvatarEntity extends BaseEntity {

    @ApiModelProperty("名字")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("类型 1:用户头像")
    @TableField("`type`")
    private Integer type;

}

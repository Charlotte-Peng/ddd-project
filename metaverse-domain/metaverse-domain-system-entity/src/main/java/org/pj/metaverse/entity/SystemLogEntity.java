package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
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
 * @since 2022-05-09 11:26:30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("system_log")
@ApiModel(value = "SystemLogEntity对象", description = "系统日志实体")
public class SystemLogEntity extends BaseEntity {

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("操作类型 0:登录 1:退出登录")
    @TableField("`type`")
    private Integer type;

    @ApiModelProperty("操作人ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("操作时间")
    @TableField("`time`")
    private LocalDateTime time;

    @ApiModelProperty("操作详情 json")
    @TableField("note")
    private String note;


}

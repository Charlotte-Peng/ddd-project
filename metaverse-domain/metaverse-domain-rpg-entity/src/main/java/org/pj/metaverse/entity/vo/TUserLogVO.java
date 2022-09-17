package org.pj.metaverse.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 16:07 2022/9/13
 **/
@Data
@Accessors(chain = true)
@ApiModel(value = "TUserLogEntity代理对象", description = "用户操作记录表")
public class TUserLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("操作类型 1:登录 2:登出 3:注册 4:修改密码 5:修改信息 6:剧情是否通过 7:游戏内操作")
    private Integer logType;

    @ApiModelProperty("日志信息")
    private String logData;

    @ApiModelProperty("操作时间")
    private LocalDateTime createTime;
}

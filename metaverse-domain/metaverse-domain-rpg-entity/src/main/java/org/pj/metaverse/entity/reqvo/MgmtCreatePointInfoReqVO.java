package org.pj.metaverse.entity.reqvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author pengjie
 * @date 9:58 2022/9/13
 **/
@Data
@ApiModel("创建关卡节点信息")
public class MgmtCreatePointInfoReqVO {

    @NotBlank(message = "关卡名称不能为空")
    @ApiModelProperty("关卡名称")
    private String name;

    @NotNull(message = "关卡类型不能为空")
    @ApiModelProperty("关卡类型 1:问答 2:商店 3:小怪 4:奖励buff 5:奖励当局货币 6:消除debuff 7:消除危险值 8:BOSS 9:剧情")
    private Integer type;

    @NotBlank(message = "关卡描述不能为空")
    @ApiModelProperty("说明文字")
    private String explain;

    @ApiModelProperty("奖励的道具id")
    private String rewardId;
}

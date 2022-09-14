package org.pj.metaverse.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author pengjie
 * @date 10:19 2022/9/13
 **/
@Data
@ApiModel("后台创建地图节点详情vo")
public class MapPointInfoVO {

    @ApiModelProperty("关卡名称")
    private String name;

    @ApiModelProperty("关卡类型 1:问答 2:商店 3:小怪 4:奖励buff 5:奖励当局货币 6:消除debuff 7:消除危险值 8:BOSS 9:剧情")
    private Integer type;

    @ApiModelProperty("说明文字")
    private List<PointInfoExplainVO> explain;

    @ApiModelProperty("奖励的道具id")
    private String rewardId;
}

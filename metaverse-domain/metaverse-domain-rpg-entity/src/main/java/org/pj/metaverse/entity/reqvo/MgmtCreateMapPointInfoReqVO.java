package org.pj.metaverse.entity.reqvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pj.metaverse.entity.vo.PointInfoExplainVO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author pengjie
 * @date 10:19 2022/9/13
 **/
@Data
@ApiModel("后台创建地图节点详情请求类")
public class MgmtCreateMapPointInfoReqVO {
    @NotBlank(message = "关卡名称不能为空")
    @ApiModelProperty("关卡名称")
    private String name;

    @NotNull(message = "关卡类型不能为空")
    @ApiModelProperty("关卡类型 1:问答 2:商店 3:小怪 4:奖励buff 5:奖励当局货币 6:消除debuff 7:消除危险值 8:BOSS 9:剧情 10:结束关卡")
    private Integer type;

    @NotBlank(message = "关卡描述不能为空")
    @ApiModelProperty("说明文字")
    private List<PointInfoExplainVO> explainData;

    @ApiModelProperty("奖励的道具id")
    private String rewardId;
}

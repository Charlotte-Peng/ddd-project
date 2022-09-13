package org.pj.metaverse.entity.reqvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author pengjie
 * @date 9:29 2022/9/13
 **/
@Data
@ApiModel("后台创建地图请求")
public class MgmtCreateMapReqVO {

    @ApiModelProperty("地图名称")
    private String mapName;

    @ApiModelProperty("地图说明")
    private String mapExplain;

    @ApiModelProperty("地图编号")
    private String mapCode;

    @ApiModelProperty("地图类型 1:剧情 2:支线 3:活动")
    private Integer mapType;

    @ApiModelProperty("地图规模 例如 3x3")
    private String mapScale;

    @ApiModelProperty("地图关卡")
    private Integer mapLevel;

    @ApiModelProperty("地图完成给予经验")
    private Long mapExpLevel;

    @ApiModelProperty("推荐完成等级")
    private Integer recommendedRank;

    @ApiModelProperty("过关要求等级")
    private String rating;

    @ApiModelProperty("每日过关次数限制")
    private Integer lockNum;

    @ApiModelProperty("上个地图id")
    private Integer parentId;

    @ApiModelProperty("地图节点信息")
    private List<MgmtCreateMapPointInfoReqVO> pointInfoListJson;

//    @ApiModelProperty("绑定的奖励(首通必给奖励)")
//    private String bindingRewardInfo;
}

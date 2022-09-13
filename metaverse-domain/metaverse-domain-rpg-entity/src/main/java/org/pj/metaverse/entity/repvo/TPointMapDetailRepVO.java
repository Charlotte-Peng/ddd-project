package org.pj.metaverse.entity.repvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pj.metaverse.entity.vo.MapPointInfoVO;
import org.pj.metaverse.entity.vo.PointInfoExplainVO;

import java.util.List;

/**
 * <p>
 * 游戏关卡信息
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Data
@ApiModel(value = "TPointMap详情返回类", description = "游戏关卡信息")
public class TPointMapDetailRepVO {

    @ApiModelProperty("主键id")
    private Integer id;

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

    @ApiModelProperty("地图信息json")
    private List<MapPointInfoVO> mapPointInfo;

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

    @ApiModelProperty("上一关地图id")
    private Integer parentId;

    @ApiModelProperty("绑定的奖励(首通必给奖励)")
    private String bindingRewardInfo;


}

package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 游戏关卡信息
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_point_map")
@ApiModel(value = "TPointMapEntity对象", description = "游戏关卡信息")
public class TPointMapEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
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

    @ApiModelProperty("地图信息")
    private String mapPointInfo;

    @ApiModelProperty("地图关卡")
    private Integer mapLevel;

    @ApiModelProperty("地图完成经验等级")
    private Long mapExperienceLevel;

    @ApiModelProperty("推荐等级")
    private Integer recommendedRank;

    @ApiModelProperty("过关要求等级")
    private String rating;

    @ApiModelProperty("每日过关次数限制")
    private Integer lockNum;

    @ApiModelProperty("下一关地图id")
    private Integer nextId;

    @ApiModelProperty("绑定的奖励(首通必给奖励)")
    private String bindingRewardInfo;


}

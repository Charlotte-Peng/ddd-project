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
 * 关卡节点相关信息
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_point_info")
@ApiModel(value = "TPointInfoEntity对象", description = "关卡节点相关信息")
public class TPointInfoEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("关卡名称")
    private String name;

    @ApiModelProperty("关卡类型 1:问答 2:商店 3:小怪 4:奖励buff 5:奖励当局货币 6:消除debuff 7:消除危险值 8:BOSS 9:剧情")
    private Integer type;

    @ApiModelProperty("说明文字json")
    private String explain;

    @ApiModelProperty("奖励的道具id")
    private String rewardId;

    @ApiModelProperty("指定节点位置(固定位置)")
    private String mapPoint;
}

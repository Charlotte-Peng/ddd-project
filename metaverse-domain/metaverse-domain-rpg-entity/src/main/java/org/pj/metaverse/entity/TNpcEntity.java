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
 * NPC信息表
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_npc")
@ApiModel(value = "TNpcEntity对象", description = "NPC信息表")
public class TNpcEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("npc名称")
    private String name;

    @ApiModelProperty("npc类型 1:仅对话npc 2:武器npc 3:防具npc 4:材料npc 5:材料npc 6:特殊商品npc 7:活动npc")
    private Integer npcType;

    @ApiModelProperty("npc模型相关数据")
    private String npcSource;

    @ApiModelProperty("0:不是剧情npc 1:剧情npc")
    private Integer npcPlot;


}

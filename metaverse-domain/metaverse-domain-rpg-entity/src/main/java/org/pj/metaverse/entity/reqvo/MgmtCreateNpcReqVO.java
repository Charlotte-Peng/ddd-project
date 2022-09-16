package org.pj.metaverse.entity.reqvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjie
 * @date 11:04 2022/9/16
 **/
@Data
@ApiModel("创建npc请求")
public class MgmtCreateNpcReqVO {

    @ApiModelProperty("npc名称")
    private String name;

    @ApiModelProperty("npc类型 1:仅对话npc 2:武器npc 3:防具npc 4:材料npc 5:材料npc 6:特殊商品npc 7:活动npc")
    private Integer npcType;

    @ApiModelProperty("npc详情数据")
    private String npcSource;

    @ApiModelProperty("是否是剧情npc 0:否 1:是")
    private Integer npcPlot;
}

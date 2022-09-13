package org.pj.metaverse.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjie
 * @date 14:35 2022/9/13
 **/
@Data
@ApiModel("地图节点文本详情类")
public class PointInfoExplainVO {

    @ApiModelProperty("文本内容")
    private String explain;

    @ApiModelProperty("npc名字")
    private String npcName;

    @ApiModelProperty("npc头像")
    private String resourceUrl;
}

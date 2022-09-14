package org.pj.metaverse.entity.reqvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjie
 * @date 10:15 2022/9/14
 **/
@Data
@ApiModel("后台创建地图节点详情vo")
public class MapMoveReqVO {

    @ApiModelProperty("地图编号")
    private String mapCode;

    @ApiModelProperty("移动到坐标x")
    private Integer x;

    @ApiModelProperty("移动到坐标y")
    private Integer y;

    @ApiModelProperty("当前会话id")
    private String sessionId;

}

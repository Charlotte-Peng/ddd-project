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
public class MgmtCreateCityReqVO {

    @ApiModelProperty("地图名称")
    private String mapName;

    @ApiModelProperty("地图说明")
    private String mapExplain;

    @ApiModelProperty("地图编号")
    private String mapCode;

    @ApiModelProperty("地图节点信息")
    private List<MgmtCreateCityPointInfoReqVO> pointInfoListJson;
}

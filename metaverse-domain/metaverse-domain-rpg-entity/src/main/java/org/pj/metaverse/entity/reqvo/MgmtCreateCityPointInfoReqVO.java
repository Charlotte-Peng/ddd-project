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
@ApiModel("后台创建城市节点详情请求类")
public class MgmtCreateCityPointInfoReqVO {

    @ApiModelProperty("npcId")
    private Integer npcId;

    @ApiModelProperty("npc对话文本")
    private List<MgmtCreateCityNpcMessageReqVO> npcMessageListJson;

}

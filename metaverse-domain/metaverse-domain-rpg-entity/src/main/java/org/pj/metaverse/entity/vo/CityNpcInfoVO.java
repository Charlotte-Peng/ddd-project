package org.pj.metaverse.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pj.metaverse.entity.reqvo.MgmtCreateCityNpcMessageReqVO;

import java.util.List;

/**
 * @author pengjie
 * @date 11:48 2022/9/15
 **/
@Data
@ApiModel("城市npc信息")
public class CityNpcInfoVO {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("npc名称")
    private String name;

    @ApiModelProperty("npc类型 1:仅对话npc 2:武器npc 3:防具npc 4:材料npc 5:材料npc 6:特殊商品npc 7:活动npc")
    private Integer npcType;

    @ApiModelProperty("npc模型相关数据")
    private String npcSource;

    @ApiModelProperty("0:不是剧情npc 1:剧情npc")
    private Integer npcPlot;

    @ApiModelProperty("npc对话文本")
    private List<MgmtCreateCityNpcMessageReqVO> npcMessageListJson;
}

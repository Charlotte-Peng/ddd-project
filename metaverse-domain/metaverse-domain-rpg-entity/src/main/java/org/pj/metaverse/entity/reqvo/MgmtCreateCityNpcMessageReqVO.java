package org.pj.metaverse.entity.reqvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjie
 * @date 10:19 2022/9/13
 **/
@Data
@ApiModel("后台创建城市npc对话详情请求类")
public class MgmtCreateCityNpcMessageReqVO {

    @ApiModelProperty("当前对话名称")
    private String name;

    @ApiModelProperty("当前对话文本")
    private String text;
}

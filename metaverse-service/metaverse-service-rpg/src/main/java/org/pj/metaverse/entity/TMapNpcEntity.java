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
 * 地图跟NPC的关联表
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_map_npc")
@ApiModel(value = "TMapNpcEntity对象", description = "地图跟NPC的关联表")
public class TMapNpcEntity {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("地图id")
    private Integer mapId;

    @ApiModelProperty("NPC id")
    private Integer npcId;

    @ApiModelProperty("npc对话消息(带选项)")
    private String npcMessage;


}

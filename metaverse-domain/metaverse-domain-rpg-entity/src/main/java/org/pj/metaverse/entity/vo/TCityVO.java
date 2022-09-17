package org.pj.metaverse.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 城镇地图信息
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Data
@ApiModel(value = "TCityVO对象", description = "城镇地图信息")
public class TCityVO {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("地图名称")
    private String mapName;

    @ApiModelProperty("地图说明")
    private String mapExplain;

    @ApiModelProperty("地图编号")
    private String mapCode;

    @ApiModelProperty("地图信息")
    private String npcInfo;

}

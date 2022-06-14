package org.pj.metaverse.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 10:10 2022/5/26
 **/
@Data
@KeySequence(value = "SEQ_MYSQL_STRING_KEY",dbType = DbType.MYSQL)
@Entity
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6557137272282292131L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Indexed
    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @Indexed
    @ApiModelProperty(value = "创建人",hidden = true)
    @TableField("create_by")
    private String createBy;

    @Indexed
    @ApiModelProperty(value = "创建时间",hidden = true)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Indexed
    @ApiModelProperty(value = "修改人",hidden = true)
    @TableField("update_by")
    private String updateBy;

    @Indexed
    @ApiModelProperty(value = "修改时间",hidden = true)
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @Indexed
    @ApiModelProperty(value = "是否删除 Y/N",hidden = true)
    @TableField("deleted")
    @TableLogic
    private String deleted;

    @Indexed
    @ApiModelProperty(value = "排序规则",hidden = true)
    @TableField(exist = false)
    private String orderBy;
}

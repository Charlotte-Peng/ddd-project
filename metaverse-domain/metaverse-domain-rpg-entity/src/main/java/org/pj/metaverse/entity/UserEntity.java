package org.pj.metaverse.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pj.metaverse.constant.ServiceNameConstant;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 16:36 2022/6/1
 **/
@Data
@NoArgsConstructor
@Entity
@RedisHash(ServiceNameConstant.SERVICE_NAME_RPG+":"+"User")
@SearchBean(
        tables = "t_user u",
        autoMapTo = "u"
)
@TableName("t_user")
public class UserEntity {
    @Id
    @ApiModelProperty(value = "主键id", example = "1q2w3e4r5t6y7u8i9o0p")
    @TableId(type = IdType.INPUT)
    private String userId;

    @Indexed
    @ApiModelProperty(value = "用户编号", example = "10000")
    private Integer userNo;

    @Indexed
    @ApiModelProperty(value = "名称", example = "pengjie")
    private String userNickName;

    @Indexed
    @ApiModelProperty(value = "登录账号", example = "pengjie")
    private String userName;

    @Indexed
    @ApiModelProperty(value = "登录密码", example = "pengjie")
    private String userPassword;

    @Indexed
    @ApiModelProperty(value = "0:女 1:男 2:未知", example = "0")
    private String sex;

    @Indexed
    @ApiModelProperty(value = "加入时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Indexed
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @Indexed
    @ApiModelProperty(value = "最近登录时间")
    private LocalDateTime loginTime;
}

package org.pj.metaverse.blog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.pj.metaverse.common.constant.ServiceNameConstant;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author pengjie
 * @date 16:36 2022/6/1
 **/
@Data
@NoArgsConstructor
@Entity
@RedisHash(ServiceNameConstant.SERVICE_NAME_BLOG+":"+"TestEntity")
public class TestEntity {
    @Id
    @ApiModelProperty(value = "主键id", example = "1q2w3e4r5t6y7u8i9o0p")
    private String id;

    @Indexed
    @ApiModelProperty(value = "名称", example = "pengjie")
    private String name;

    @Indexed
    @ApiModelProperty(value = "年龄", example = "18")
    private String age;

    @Indexed
    @ApiModelProperty(value = "性别", example = "男")
    private String sex;
}

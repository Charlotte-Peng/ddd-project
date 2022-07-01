package org.pj.metaverse.entity.repvo;

import com.baomidou.mybatisplus.annotation.*;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.InheritType;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pj.metaverse.entity.UserEntity;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 14:40 2022/7/1
 **/
@ApiModel(description = "用户详情列表返回实体")
@SearchBean(tables = "user u,user_role ur,role r",
            joinCond = "u.user_id = ur.user_id and ur.role_id = r.id",
            orderBy = "u.create_time desc")
@Data
public class UserRoleInfoRepVO {
    @Id
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.INPUT)
    @DbField(value = "u.user_id")
    private String id;

    @Indexed
    @ApiModelProperty(value = "多租户id")
    @DbField(value = "u.tenant_id")
    private Long tenantId;

    @Indexed
    @ApiModelProperty("排序")
    @TableField("sort")
    @DbField(value = "u.sort")
    private Integer sort;

    @Indexed
    @ApiModelProperty(value = "创建人",hidden = true)
    @TableField("create_by")
    @DbField(value = "u.create_by")
    private String createBy;

    @Indexed
    @ApiModelProperty(value = "创建时间",hidden = true)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @DbField(value = "u.create_time")
    private LocalDateTime createTime;

    @Indexed
    @ApiModelProperty(value = "修改人",hidden = true)
    @TableField("update_by")
    @DbField(value = "u.update_by")
    private String updateBy;

    @Indexed
    @ApiModelProperty(value = "修改时间",hidden = true)
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @DbField(value = "u.update_time")
    private LocalDateTime updateTime;

    @Indexed
    @ApiModelProperty(value = "是否删除 Y/N",hidden = true)
    @TableField("deleted")
    @TableLogic
    @DbField(value = "u.deleted")
    private String deleted;

    @Indexed
    @ApiModelProperty("用户id")
    @TableField("u.user_id")
    private String userId;

    @Indexed
    @ApiModelProperty("用户编号")
    @TableField("user_no")
    @DbField(value = "u.user_no")
    private Integer userNo;

    @Indexed
    @ApiModelProperty("用户昵称")
    @TableField("user_nick_name")
    @DbField(value = "u.user_nick_name")
    private String userNickName;

    @Indexed
    @ApiModelProperty("用户头像")
    @TableField("user_avatar")
    @DbField(value = "u.user_avatar")
    private String userAvatar;

    @Indexed
    @ApiModelProperty("生日")
    @TableField("birthday")
    @DbField(value = "u.birthday")
    private LocalDate birthday;

    @Indexed
    @ApiModelProperty("年龄")
    @TableField("age")
    @DbField(value = "u.age")
    private Integer age;

    @Indexed
    @ApiModelProperty("0:未知 1:南 2:女")
    @TableField("sex")
    @DbField(value = "u.sex")
    private Integer sex;

    @DbField(value = "r.name",alias = "roleName")
    private String roleName;
}

package org.pj.metaverse.entity.repvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.pj.metaverse.entity.vo.PermissionVO;

import java.util.List;

/**
 * @author pengjie
 * @date 15:03 2022/5/17
 **/
@Data
public class LoginRepVO {

    @ApiModelProperty("token名称")
    private String tokenName;

    @ApiModelProperty("token值")
    private String tokenValue;

    @ApiModelProperty("当前用户的userId")
    private String userId;

    @ApiModelProperty("名称")
    private String userNickName;

    @ApiModelProperty("用户头像")
    private String userAvatar;

    @ApiModelProperty("当前用户的权限集合")
    private List<PermissionVO> permissionList;
}

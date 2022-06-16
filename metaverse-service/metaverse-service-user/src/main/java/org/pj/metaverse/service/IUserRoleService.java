package org.pj.metaverse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.entity.UserRoleEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
public interface IUserRoleService extends IService<UserRoleEntity> {
    /**
     * 将用户跟角色进行绑定
     * @author pengjie
     * @date 2022/5/17 17:37
     * @param userId 用户id
     * @param type 类型 1:app端用户 2:web端用户
     * @param roleId 角色id
     */
    void roleBinding(String userId ,Integer type, Integer roleId);
}

package org.pj.metaverse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.entity.RoleEntity;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
public interface IRoleService extends IService<RoleEntity> {


    /**
     * 查询角色列表
     * @author pengjie
     * @date 2022/5/10 17:30
     * @return java.util.List<org.pj.metaverse.user.entity.RoleEntity>
     */
    List<RoleEntity> getRoleList ();

    /**
     * 创建角色
     * @author pengjie
     * @date 2022/5/11 11:28
     * @param entity 角色实体对象
     * @return org.pj.common.entity.result.DataResult<java.lang.Void>
     */
    DataResult<Void> addRole(RoleEntity entity);

    /**
     * 批量写入
     * @author pengjie
     * @date 2022/5/11 11:32
     * @param entity 创建对象集合
     * @return org.pj.common.entity.result.DataResult<java.lang.Void>
     */
    DataResult<Void> addBatchRole(List<RoleEntity> entity);

    /**
     * 根据用户信息查新角色信息
     * @author pengjie
     * @date 2022/5/11 11:32
     * @return org.pj.common.entity.result.DataResult<java.lang.Void>
     */
    DataResult<Void> getRole();

    DataResult<Void> updateRole(RoleEntity entity);
}

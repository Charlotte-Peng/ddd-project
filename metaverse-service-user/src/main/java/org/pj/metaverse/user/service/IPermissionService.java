package org.pj.metaverse.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.common.result.DataResult;
import org.pj.metaverse.user.entity.PermissionEntity;

import java.util.List;

/**
 * <p>
 * 权限接口表 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
public interface IPermissionService extends IService<PermissionEntity> {

    /**
     * 查询权限列表
     * @author pengjie
     * @date 2022/5/10 17:27
     * @return java.util.List<org.pj.metaverse.user.entity.PermissionEntity>
     */
    List<PermissionEntity> getPermissionList();

    /**
     * 添加权限
     * @author pengjie
     * @date 2022/5/11 11:40
     * @param entity 权限实体
     * @return org.pj.common.entity.result.DataResult<java.lang.Void>
     */
    DataResult<Void> addPermission(PermissionEntity entity);

    /**
     * 批量添加权限
     * @author pengjie
     * @date 2022/5/11 11:41
     * @param entity
     * @return org.pj.common.entity.result.DataResult<java.lang.Void>
     */
    DataResult<Void> addBatchPermission(List<PermissionEntity> entity);
}

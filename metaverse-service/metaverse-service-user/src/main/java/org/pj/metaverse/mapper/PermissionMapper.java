package org.pj.metaverse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pj.metaverse.entity.PermissionEntity;

import java.util.List;

/**
 * <p>
 * 权限接口表 Mapper 接口
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
public interface PermissionMapper extends BaseMapper<PermissionEntity> {
    /**
     * 根据userId查询权限集合
     * @author pengjie
     * @date 2022/5/10 17:37
     * @param userId
     * @return java.util.List<org.pj.metaverse.user.entity.PermissionEntity>
     */
    List<PermissionEntity> getPermissionList(String userId);
}

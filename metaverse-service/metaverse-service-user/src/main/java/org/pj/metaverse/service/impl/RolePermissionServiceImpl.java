package org.pj.metaverse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.RolePermissionEntity;
import org.pj.metaverse.mapper.RolePermissionMapper;
import org.pj.metaverse.service.IRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionEntity> implements IRolePermissionService {

}

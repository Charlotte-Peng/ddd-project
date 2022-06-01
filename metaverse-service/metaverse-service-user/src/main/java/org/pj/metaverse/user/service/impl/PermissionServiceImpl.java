package org.pj.metaverse.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.exception.ServerException;
import org.pj.metaverse.common.result.DataResult;
import org.pj.metaverse.common.enums.ResponseEnum;
import org.pj.metaverse.user.entity.PermissionEntity;
import org.pj.metaverse.user.mapper.PermissionMapper;
import org.pj.metaverse.user.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限接口表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements IPermissionService {
    private final PermissionMapper permissionMapper;

    @Override
    public List<PermissionEntity> getPermissionList() {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        return permissionMapper.getPermissionList(loginId);
    }

    @Override
    public DataResult<Void> addPermission(PermissionEntity entity) {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        entity.setCreateBy(loginId);
        boolean save = this.save(entity);
        if (!save){
            throw new ServerException(ResponseEnum.SYSTEM_ERROR);
        }
        return DataResult.success();
    }

    @Override
    public DataResult<Void> addBatchPermission(List<PermissionEntity> entity) {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        for (PermissionEntity permissionEntity : entity) {
            permissionEntity.setCreateBy(loginId);
        }
        boolean save = this.saveBatch(entity);
        if (!save){
            throw new ServerException(ResponseEnum.SYSTEM_ERROR);
        }
        return DataResult.success();
    }
}

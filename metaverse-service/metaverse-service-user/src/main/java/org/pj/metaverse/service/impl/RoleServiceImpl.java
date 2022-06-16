package org.pj.metaverse.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.RoleEntity;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.mapper.RoleMapper;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.enums.ResponseEnum;
import org.pj.metaverse.service.IRoleService;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements IRoleService {
    private final RoleMapper roleMapper;

    @Override
    public List<RoleEntity> getRoleList() {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        return roleMapper.getRoleList(loginId);
    }

    @Override
    public DataResult<Void> addRole(RoleEntity entity) {
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
    public DataResult<Void> addBatchRole(List<RoleEntity> entity) {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        for (RoleEntity roleEntity : entity) {
            roleEntity.setCreateBy(loginId);
        }
        boolean save = this.saveBatch(entity);
        if (!save){
            throw new ServerException(ResponseEnum.SYSTEM_ERROR);
        }
        return DataResult.success();
    }

    @Override
    public DataResult<Void> getRole() {
        // 获取当前人登录id
        String loginId = StpUtil.getLoginIdAsString();
        return null;
    }

    @Override
    public DataResult<Void> updateRole(RoleEntity entity) {
        if (NvlUtils.isNull(entity)){
            throw new ServerException(ResponseEnum.THE_PARAMETER_IS_WRONG);
        }
        if (NvlUtils.isNull(entity.getId())){
            throw new ServerException(ResponseEnum.THE_PARAMETER_IS_WRONG);
        }
        if (NvlUtils.isNotBlank(entity.getName())){
            throw new ServerException(ResponseEnum.THE_PARAMETER_IS_WRONG);
        }
        boolean b = this.updateById(entity);
        if (!b){
            throw new ServerException(ResponseEnum.SYSTEM_ERROR);
        }
        return DataResult.success();
    }
}

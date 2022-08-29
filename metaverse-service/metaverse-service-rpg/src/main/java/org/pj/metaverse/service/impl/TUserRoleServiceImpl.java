package org.pj.metaverse.service.impl;

import org.pj.metaverse.entity.TUserRoleEntity;
import org.pj.metaverse.mapper.TUserRoleMapper;
import org.pj.metaverse.service.ITUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号的角色关联表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Service
public class TUserRoleServiceImpl extends ServiceImpl<TUserRoleMapper, TUserRoleEntity> implements ITUserRoleService {

}

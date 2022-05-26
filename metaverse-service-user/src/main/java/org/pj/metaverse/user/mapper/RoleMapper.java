package org.pj.metaverse.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.pj.metaverse.user.entity.RoleEntity;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 根据登陆人的信息查询对应的角色信息
     * @param userId 用户id
     * @return 角色对象集合
     */
    List<RoleEntity> getRoleList(String userId);
}

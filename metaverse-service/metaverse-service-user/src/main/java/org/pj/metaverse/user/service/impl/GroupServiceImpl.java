package org.pj.metaverse.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pj.metaverse.user.entity.GroupEntity;
import org.pj.metaverse.user.mapper.GroupMapper;
import org.pj.metaverse.user.service.IGroupService;
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
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupEntity> implements IGroupService {

}

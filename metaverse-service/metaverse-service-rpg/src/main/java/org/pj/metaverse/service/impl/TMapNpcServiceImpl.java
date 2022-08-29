package org.pj.metaverse.service.impl;

import org.pj.metaverse.entity.TMapNpcEntity;
import org.pj.metaverse.mapper.TMapNpcMapper;
import org.pj.metaverse.service.ITMapNpcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地图跟NPC的关联表 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Service
public class TMapNpcServiceImpl extends ServiceImpl<TMapNpcMapper, TMapNpcEntity> implements ITMapNpcService {

}

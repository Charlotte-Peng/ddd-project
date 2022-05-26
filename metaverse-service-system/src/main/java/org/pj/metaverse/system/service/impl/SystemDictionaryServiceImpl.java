package org.pj.metaverse.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.pj.metaverse.common.enums.ResponseEnum;
import org.pj.metaverse.common.exception.ServerException;
import org.pj.metaverse.system.entity.SystemDictionaryEntity;
import org.pj.metaverse.system.mapper.SystemDictionaryMapper;
import org.pj.metaverse.system.service.ISystemDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-09 11:26:30
 */
@Service
public class SystemDictionaryServiceImpl extends ServiceImpl<SystemDictionaryMapper, SystemDictionaryEntity> implements ISystemDictionaryService {

    @Override
    public String getDictionary(String key) {
        SystemDictionaryEntity one = this.lambdaQuery()
                .eq(SystemDictionaryEntity::getKey,key)
                .eq(SystemDictionaryEntity::getEnable,SystemDictionaryEntity.ENABLE_CONSTANT_ENABLE)
                .one();
        if (one == null){
            throw new ServerException(ResponseEnum.DATA_DOES_NOT_EXIST);
        }
        return one.getValue();
    }

    @Override
    public void addDictionary(SystemDictionaryEntity entity) {
        // 获取登录人的信息
        String loginId = StpUtil.getLoginIdAsString();
        // 写入公共信息
        entity.setCreateBy(loginId);
        // 将实体写入
        boolean save = this.save(entity);
        if (!save){
            throw new ServerException(ResponseEnum.SYSTEM_ERROR);
        }
    }
}

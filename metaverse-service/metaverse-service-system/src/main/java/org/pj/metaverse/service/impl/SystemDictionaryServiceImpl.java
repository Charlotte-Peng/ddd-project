package org.pj.metaverse.service.impl;

import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.SystemDictionaryEntity;
import org.pj.metaverse.enums.ResponseEnum;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.utils.AsyncUtils;
import org.pj.metaverse.mapper.SystemDictionaryMapper;
import org.pj.metaverse.repository.redis.SystemDictionaryRepositoryRedis;
import org.pj.metaverse.service.ISystemDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-09 11:26:30
 */
@Service
@RequiredArgsConstructor
public class SystemDictionaryServiceImpl extends ServiceImpl<SystemDictionaryMapper, SystemDictionaryEntity> implements ISystemDictionaryService {
    private final SystemDictionaryRepositoryRedis repositoryRedis;
    private final AsyncUtils asyncUtils;

    @Override
    public String getDictionary(String key) {
        // 根据key跟enable查询
        SystemDictionaryEntity entity = repositoryRedis.findByKeyAndEnable(key, SystemDictionaryEntity.ENABLE_CONSTANT_ENABLE);
        /*SystemDictionaryEntity one = this.lambdaQuery()
                .eq(SystemDictionaryEntity::getKey,key)
                .eq(SystemDictionaryEntity::getEnable,SystemDictionaryEntity.ENABLE_CONSTANT_ENABLE)
                .one();*/
        if (entity == null){
            throw new ServerException(ResponseEnum.DATA_DOES_NOT_EXIST);
        }
        return entity.getValue();
    }

    @Override
    public void addDictionary(SystemDictionaryEntity entity) {
       /* // 获取登录人的信息
        String loginId = StpUtil.getLoginIdAsString();
        // 写入公共信息
        entity.setCreateBy(loginId);*/
        // 将实体写入缓存
        repositoryRedis.save(entity);
        // 异步写入数据库
        asyncUtils.exec(() -> {
            boolean save = this.save(entity);
            if (!save){
                log.error("写入数据库失败");
            }
        });
    }

    @Override
    public void updateDictionary(SystemDictionaryEntity entity) {

        // 查询原数据
        SystemDictionaryEntity oldEntity = repositoryRedis.findById(entity.getId()).orElse(null);
        if (oldEntity == null){
            throw new ServerException(ResponseEnum.DATA_DOES_NOT_EXIST);
        }
       /* // 获取登录人的信息
        String loginId = StpUtil.getLoginIdAsString();
        // 写入公共信息
        oldEntity.setUpdateBy(loginId);*/
        if (entity.getEnable() != null){
            oldEntity.setEnable(entity.getEnable());
        }
        if (entity.getValue() != null){
            oldEntity.setValue(entity.getValue());
        }
        oldEntity.setUpdateTime(LocalDateTime.now());
        // 将实体写入缓存
        repositoryRedis.save(oldEntity);
        // 异步写入数据库
        asyncUtils.exec(() -> {
            boolean update = this.updateById(oldEntity);
            if (!update){
                log.error("写入数据库失败");
            }
        });
    }
}

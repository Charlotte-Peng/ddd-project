package org.pj.metaverse.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import de.huxhorn.sulky.ulid.ULID;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.enums.ResponseEnum;
import org.pj.metaverse.common.exception.ServerException;
import org.pj.metaverse.common.utils.AsyncUtils;
import org.pj.metaverse.system.entity.SystemDictionaryEntity;
import org.pj.metaverse.system.mapper.SystemDictionaryMapper;
import org.pj.metaverse.system.repository.redis.SystemDictionaryRepositoryRedis;
import org.pj.metaverse.system.service.ISystemDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        // 获取登录人的信息
        String loginId = StpUtil.getLoginIdAsString();
        // 写入公共信息
        entity.setCreateBy(loginId);
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
}

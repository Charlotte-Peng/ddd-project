package org.pj.metaverse.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.constant.SystemDictionaryConstant;
import org.pj.metaverse.system.constant.RedisConstant;
import org.pj.metaverse.system.entity.BaseNameEntity;
import org.pj.metaverse.system.mapper.BaseNameMapper;
import org.pj.metaverse.system.service.IBaseNameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pj.metaverse.system.service.ISystemDictionaryService;
import org.pj.metaverse.common.utils.NvlUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统通用昵称 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-17 16:36:21
 */
@Service
@RequiredArgsConstructor
public class BaseNameServiceImpl extends ServiceImpl<BaseNameMapper, BaseNameEntity> implements IBaseNameService {
    private final StringRedisTemplate stringRedisTemplate;
    private final ISystemDictionaryService systemDictionaryService;

    @SuppressWarnings({"AlibabaUndefineMagicConstant", "synchronization","rawtypes"})
    @Override
    public Set<String> randomlyObtainTheNameOfTheSpecifiedType(Integer type, Integer num) {
        // 查询redis中是否有数据
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(RedisConstant.NAME+":"+type))){
            // 没有,采用双重校验锁
            synchronized (type){
                if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(RedisConstant.NAME+":"+type))){
                    // 还是没有，从数据库查询
                    List<BaseNameEntity> list = this.lambdaQuery()
                            .eq(BaseNameEntity::getType, type)
                            .list();
                    String dictionaryJson = systemDictionaryService.getDictionary(SystemDictionaryConstant.SYSTEM_NAME_KEY);
                    Map map = JSONObject.parseObject(dictionaryJson, Map.class);
                    if (NvlUtils.isNull(list)){
                        // 没有数据
                        String nameUrl = (String) map.get(SystemDictionaryConstant.SYSTEM_NAME_DEFAULT_VALUE_KEY);
                        HashSet<String> strings = new HashSet<>();
                        for (int i = 0; i < num ; i++) {
                            stringRedisTemplate.opsForSet().add(RedisConstant.NAME+":"+type,nameUrl);
                            strings.add(nameUrl);
                        }
                        Integer initTime = (Integer) map.get(SystemDictionaryConstant.SYSTEM_NAME_INIT_VALUE_KEY);
                        stringRedisTemplate.expire(RedisConstant.NAME+":"+type,initTime, TimeUnit.MINUTES);
                        return strings;
                    }
                    // 有头像，写入redis，并设置过期时间为设置天
                    Set<String> strings = list.stream().map(BaseNameEntity::getName).collect(Collectors.toSet());
                    strings.forEach(item -> stringRedisTemplate.opsForSet().add(RedisConstant.NAME+":"+type,item));
                    Integer time = (Integer) map.get(SystemDictionaryConstant.SYSTEM_NAME_VALUE_KEY);
                    stringRedisTemplate.expire(RedisConstant.NAME+":"+type,time, TimeUnit.DAYS);
                    // 随机取出指定数量
                    return stringRedisTemplate.opsForSet().distinctRandomMembers(RedisConstant.NAME + ":" + type, num);
                }
            }

        }
        // 第一遍有
        // 随机取出指定数量
        return stringRedisTemplate.opsForSet().distinctRandomMembers(RedisConstant.NAME + ":" + type, num);
    }
}

package org.pj.metaverse.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.constant.SystemDictionaryConstant;
import org.pj.metaverse.system.mapper.BaseAvatarMapper;
import org.pj.metaverse.system.constant.RedisConstant;
import org.pj.metaverse.system.entity.BaseAvatarEntity;
import org.pj.metaverse.system.service.IBaseAvatarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pj.metaverse.system.service.ISystemDictionaryService;
import org.pj.metaverse.utils.common.NvlUtils;
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
 * 系统通用头像 服务实现类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-17 16:36:21
 */
@Service
@RequiredArgsConstructor
public class BaseAvatarServiceImpl extends ServiceImpl<BaseAvatarMapper, BaseAvatarEntity> implements IBaseAvatarService {
    private final StringRedisTemplate stringRedisTemplate;
    private final ISystemDictionaryService systemDictionaryService;

    @SuppressWarnings({"AlibabaUndefineMagicConstant", "synchronization","rawtypes"})
    @Override
    public Set<String> randomlyObtainTheAvatarOfTheSpecifiedType(Integer type, Integer num) {
        // 查询redis中是否有数据
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(RedisConstant.AVATAR+":"+type))){
            // 没有,采用双重校验锁
            synchronized (type){
                if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(RedisConstant.AVATAR+":"+type))){
                    // 还是没有，从数据库查询
                    List<BaseAvatarEntity> list = this.lambdaQuery()
                            .eq(BaseAvatarEntity::getType, type)
                            .list();
                    String dictionaryJson = systemDictionaryService.getDictionary(SystemDictionaryConstant.SYSTEM_AVATAR_KEY);
                    Map map = JSONObject.parseObject(dictionaryJson, Map.class);
                    if (NvlUtils.isNull(list)){
                        // 没有数据
                        String avatarUrl = (String) map.get(SystemDictionaryConstant.SYSTEM_AVATAR_DEFAULT_VALUE_KEY);
                        HashSet<String> strings = new HashSet<>();
                        for (int i = 0; i < num ; i++) {
                            stringRedisTemplate.opsForSet().add(RedisConstant.AVATAR+":"+type,avatarUrl);
                            strings.add(avatarUrl);
                        }
                        Integer initTime = (Integer) map.get(SystemDictionaryConstant.SYSTEM_AVATAR_INIT_VALUE_KEY);
                        stringRedisTemplate.expire(RedisConstant.AVATAR+":"+type,initTime, TimeUnit.MINUTES);
                        return strings;
                    }
                    // 有头像，写入redis，并设置过期时间为设置天
                    Set<String> strings = list.stream().map(BaseAvatarEntity::getAvatar).collect(Collectors.toSet());
                    strings.forEach(item -> stringRedisTemplate.opsForSet().add(RedisConstant.AVATAR+":"+type,item));
                    Integer time = (Integer) map.get(SystemDictionaryConstant.SYSTEM_AVATAR_VALUE_KEY);
                    stringRedisTemplate.expire(RedisConstant.AVATAR+":"+type,time, TimeUnit.DAYS);
                    // 随机取出指定数量
                    return stringRedisTemplate.opsForSet().distinctRandomMembers(RedisConstant.AVATAR + ":" + type, num);
                }
            }

        }
        // 第一遍有
        // 随机取出指定数量
        return stringRedisTemplate.opsForSet().distinctRandomMembers(RedisConstant.AVATAR + ":" + type, num);
    }
}

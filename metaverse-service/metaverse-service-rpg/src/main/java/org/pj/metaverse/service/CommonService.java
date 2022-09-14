package org.pj.metaverse.service;

import lombok.RequiredArgsConstructor;
import org.pj.metaverse.constant.CommonRedisConstant;
import org.pj.metaverse.entity.TDictionaryEntity;
import org.pj.metaverse.mapper.TDictionaryMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author pengjie
 * @date 15:55 2022/8/25
 **/
@Component
@RequiredArgsConstructor
public class CommonService {
    private final StringRedisTemplate redisTemplate;
    private final TDictionaryMapper dictionaryMapper;
    /**
     * 获取常量所有常量信息
     */
    public List<TDictionaryEntity> getAllConstants() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(CommonRedisConstant.CONSTANTS);
        // 将map转成字典类
        Set<Map.Entry<Object, Object>> entries1 = entries.entrySet();
        List<TDictionaryEntity> collect = entries1.stream().map(item -> {
            TDictionaryEntity dictionary = new TDictionaryEntity();
            dictionary.setKey((String) item.getKey());
            dictionary.setValue((String) item.getValue());
            return dictionary;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 获取指定常量信息
     */
    public TDictionaryEntity getConstant(String key) {
        TDictionaryEntity dictionary = new TDictionaryEntity();
        dictionary.setKey(key);
        dictionary.setValue((String) redisTemplate.opsForHash().get(CommonRedisConstant.CONSTANTS, key));
        return dictionary;
    }

    /**
     * 初始化常量信息
     */
    public void initConstants() {
        List<TDictionaryEntity> tDictionaryEntities = dictionaryMapper.selectList(null);
        // 初始化常量信息
        Map<String, String> data = new HashMap<>(tDictionaryEntities.size());
        for (TDictionaryEntity tDictionaryEntity : tDictionaryEntities) {
            data.put(tDictionaryEntity.getKey(), tDictionaryEntity.getValue());
        }
        redisTemplate.opsForHash().putAll(CommonRedisConstant.CONSTANTS, data);
    }
}

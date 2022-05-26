package org.pj.metaverse.system.service;

import org.pj.metaverse.system.entity.SystemDictionaryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-09 11:26:30
 */
public interface ISystemDictionaryService extends IService<SystemDictionaryEntity> {

    /**
     * 根据 key查询对应详情
     * @param key 建
     * @return 值(json)
     */
    String getDictionary(String key);

    /**
     * 添加字典信息
     * @param entity 添加的实体
     */
    void addDictionary(SystemDictionaryEntity entity);
}

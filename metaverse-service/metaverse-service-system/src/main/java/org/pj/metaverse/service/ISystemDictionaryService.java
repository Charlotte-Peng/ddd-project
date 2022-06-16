package org.pj.metaverse.service;

import org.pj.metaverse.entity.SystemDictionaryEntity;
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

    /**
     * 修改字典信息
     * @author pengjie
     * @date 2022/6/15 16:09
     * @param entity 修改的实体
     */
    void updateDictionary(SystemDictionaryEntity entity);
}

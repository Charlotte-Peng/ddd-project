package org.pj.metaverse.service;

import org.pj.metaverse.entity.BaseNameEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 系统通用昵称 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-17 16:36:21
 */
public interface IBaseNameService extends IService<BaseNameEntity> {
    /**
     * 随机获取指定类型的名称
     * @author pengjie
     * @date 2022/5/26 15:25
     * @param type 类型id
     * @param num 个数
     * @return java.util.Set<java.lang.String>
     */
    Set<String> randomlyObtainTheNameOfTheSpecifiedType(Integer type, Integer num);
}

package org.pj.metaverse.system.service;

import org.pj.metaverse.system.entity.BaseAvatarEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 系统通用头像 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-17 16:36:21
 */
public interface IBaseAvatarService extends IService<BaseAvatarEntity> {

    /**
     * 随机获取指定类型的头像
     * @author pengjie
     * @date 2022/5/26 15:25
     * @param type 类型id
     * @param num 个数
     * @return java.util.Set<java.lang.String>
     */
    Set<String> randomlyObtainTheAvatarOfTheSpecifiedType(Integer type, Integer num);
}

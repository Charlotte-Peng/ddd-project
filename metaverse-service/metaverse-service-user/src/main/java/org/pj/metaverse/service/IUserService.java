package org.pj.metaverse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.entity.UserEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-08 13:54:53
 */
public interface IUserService extends IService<UserEntity> {

    /**
     * 查询在线用户数量
     * @author pengjie
     * @date 2022/5/30 9:44
     * @return java.lang.Long
     */
    Long getLineCount();
}

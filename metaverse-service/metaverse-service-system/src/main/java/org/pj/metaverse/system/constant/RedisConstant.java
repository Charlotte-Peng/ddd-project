package org.pj.metaverse.system.constant;


import org.pj.metaverse.common.constant.redis.ParentConstant;

/**
 * @author pengjie
 * @date 16:44 2022/5/17
 **/
public interface RedisConstant {
    String PARENT = ParentConstant.REDIS_NAME + "SYSTEM:";
    String AVATAR = PARENT + "AVATAR";
    String NAME = PARENT + "NAME";
}

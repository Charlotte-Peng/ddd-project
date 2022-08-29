package org.pj.metaverse.constant;

import org.pj.metaverse.constant.redis.ParentConstant;
import org.pj.metaverse.enums.CommonEnum;

/**
 * @author pengjie
 * @date 16:00 2022/8/25
 **/
public interface CommonRedisConstant{
    String CONSTANTS = ParentConstant.REDIS_NAME + "RPG" + ":CONSTANTS";
}

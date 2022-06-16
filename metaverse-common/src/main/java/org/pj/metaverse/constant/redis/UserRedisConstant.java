package org.pj.metaverse.constant.redis;

/**
 * @author pengjie
 * @date 11:43 2022/5/10
 **/
public interface UserRedisConstant {
    String BASE_NAME = ParentConstant.REDIS_NAME +"USER:";
    String USER_ROLE = BASE_NAME +"ROLE";
    String USER_ONLINE_NUM = BASE_NAME +"ONLINE_NUM";
}

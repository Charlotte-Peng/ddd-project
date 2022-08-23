package org.pj.metaverse.service;

import org.pj.metaverse.entity.UserEntity;
import org.pj.metaverse.entity.reqvo.UserLoginReqVO;
import org.pj.metaverse.entity.reqvo.UserRegisterReqVO;

/**
 * @author pengjie
 * @date 17:51 2022/8/23
 **/
public interface UserService {
    /**
     * 用户注册
     * @author pengjie
     * @date 2022/8/23 17:52
     * @param vo 用户注册信息
     * @return org.pj.metaverse.entity.UserEntity
     */
    UserEntity register(UserRegisterReqVO vo);

    /**
     * 用户登录
     * @author pengjie
     * @date 2022/8/23 17:52
     * @param vo 用户登录信息
     * @return org.pj.metaverse.entity.UserEntity
     */
    UserEntity login(UserLoginReqVO vo);
}

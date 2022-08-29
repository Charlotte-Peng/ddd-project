package org.pj.metaverse.service;

import org.pj.metaverse.entity.TUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.entity.reqvo.UserLoginReqVO;
import org.pj.metaverse.entity.reqvo.UserRegisterReqVO;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
public interface ITUserService extends IService<TUserEntity> {

    /**
     * 注册
     * @author pengjie
     * @date 2022/8/25 15:10
     * @param vo 注册参数
     * @return org.pj.metaverse.entity.TUserEntity
     */
    TUserEntity register(UserRegisterReqVO vo);

    /**
     * 登录
     * @author pengjie
     * @date 2022/8/25 15:10
     * @param vo 登录参数
     * @return org.pj.metaverse.entity.TUserEntity
     */
    TUserEntity login(UserLoginReqVO vo);
}

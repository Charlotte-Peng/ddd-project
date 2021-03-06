package org.pj.metaverse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pj.metaverse.entity.LoginEntity;
import org.pj.metaverse.entity.repvo.LoginRepVO;
import org.pj.metaverse.entity.reqvo.RegisteredAccountReqVO;
import org.pj.metaverse.entity.reqvo.UserLoginReqVO;
import org.pj.metaverse.entity.reqvo.WebRegisteredAccountReqVO;
import org.pj.metaverse.entity.reqvo.WebUserLoginReqVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pengjie
 * @since 2022-05-08 14:05:12
 */
public interface ILoginService extends IService<LoginEntity> {

    /**
     * app根据账号密码进行查询登录
     * @param vo 账号密码实体
     * @return LoginRepVO 信息
     */
    LoginRepVO accountPasswordLoginV1(UserLoginReqVO vo);


    /**
     * web根据账号密码进行查询登录
     * @author pengjie
     * @date 2022/5/17 15:12
     * @param vo 账号密码验证码实体
     * @param sessionId 当前账号会话id
     * @return org.pj.system.entity.repvo.LoginRepVO
     */
    LoginRepVO webAccountPasswordLoginV1(WebUserLoginReqVO vo, String sessionId);

    /**
     * 用户注册调用的接口
     * @author pengjie
     * @date 2022/5/17 16:05
     * @param vo 注册的实体类
     * @return org.pj.system.entity.repvo.LoginRepVO
     */
    LoginRepVO registeredAccount(RegisteredAccountReqVO vo);

    /**
     * web用户注册调用的接口
     * @author pengjie
     * @date 2022/5/17 16:05
     * @param vo 注册的实体类
     * @return org.pj.system.entity.repvo.LoginRepVO
     */
    LoginRepVO webRegisteredAccount(WebRegisteredAccountReqVO vo);

    /**
     * 退出登录
     * @author pengjie
     * @param device 设备类型 APP WEB
     * @date 2022/5/27 14:02
     */
    void logOut(String device);
}

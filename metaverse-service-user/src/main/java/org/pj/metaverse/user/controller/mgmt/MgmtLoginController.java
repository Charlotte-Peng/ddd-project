package org.pj.metaverse.user.controller.mgmt;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.result.DataResult;
import org.pj.metaverse.common.enums.ResponseEnum;
import org.pj.metaverse.user.entity.repvo.LoginRepVO;
import org.pj.metaverse.user.entity.reqvo.WebRegisteredAccountReqVO;
import org.pj.metaverse.user.entity.reqvo.WebUserLoginReqVO;
import org.pj.metaverse.user.service.ILoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-08 14:05:12
 */
@Api(tags = "【Web】登陆相关的接口")
@Controller
@RequestMapping("mgmt/login")
@RequiredArgsConstructor
public class MgmtLoginController {
    private final ILoginService loginService;


    @ApiOperation(value = "【Web】账号密码登录")
    @PostMapping("login-v1")
    public DataResult<LoginRepVO> webAccountPasswordLoginV1(@RequestBody WebUserLoginReqVO vo, HttpServletRequest request){
        String sessionId = request.getSession().getId();
        LoginRepVO data = loginService.webAccountPasswordLoginV1(vo,sessionId);
        return new DataResult<>(DataResult.SUCCESS_CODE,DataResult.SUCCESS_MESSAGE,data);
    }

    @ApiOperation("【Web】注册用户")
    @PostMapping("register")
    public DataResult<LoginRepVO> webRegisteredAccount(@RequestBody WebRegisteredAccountReqVO vo){
        LoginRepVO data = loginService.webRegisteredAccount(vo);
        return new DataResult<>(ResponseEnum.SUCCESS,data);
    }
}


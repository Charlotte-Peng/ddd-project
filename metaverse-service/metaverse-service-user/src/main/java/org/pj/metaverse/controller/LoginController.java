package org.pj.metaverse.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.enums.ResponseEnum;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.ILoginService;
import org.pj.metaverse.entity.repvo.LoginRepVO;
import org.pj.metaverse.entity.reqvo.RegisteredAccountReqVO;
import org.pj.metaverse.entity.reqvo.UserLoginReqVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-08 14:05:12
 */
@Api(tags = "登陆相关的接口")
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final ILoginService loginService;



    @ApiOperation(value = "【App】账号密码登录")
    @PostMapping("login-v1")
    public DataResult<LoginRepVO> accountPasswordLoginV1(@RequestBody UserLoginReqVO vo){
        LoginRepVO data = loginService.accountPasswordLoginV1(vo);
        return new DataResult<>(DataResult.SUCCESS_CODE,DataResult.SUCCESS_MESSAGE,data);
    }

    @ApiOperation(value = "【App】退出登录")
    @PostMapping("logOut-v1")
    public DataResult<Void> logOut(){
        loginService.logOut("APP");
        return new DataResult<>(DataResult.SUCCESS_CODE,DataResult.SUCCESS_MESSAGE);
    }

    @ApiOperation("【App】注册用户")
    @PostMapping("register")
    public DataResult<LoginRepVO> registeredAccount(@RequestBody RegisteredAccountReqVO vo){
        LoginRepVO data = loginService.registeredAccount(vo);
        return new DataResult<>(ResponseEnum.SUCCESS,data);
    }
}


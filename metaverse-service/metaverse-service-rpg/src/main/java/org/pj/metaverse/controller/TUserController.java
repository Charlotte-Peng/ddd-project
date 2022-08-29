package org.pj.metaverse.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TUserEntity;
import org.pj.metaverse.entity.reqvo.UserLoginReqVO;
import org.pj.metaverse.entity.reqvo.UserRegisterReqVO;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.ITUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Api(tags = "用户相关操作")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TUserController {
    private final ITUserService userService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public DataResult<TUserEntity> register(@RequestBody @Valid UserRegisterReqVO vo) {
        TUserEntity data = userService.register(vo);
        return new DataResult<>(data);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public DataResult<TUserEntity> login(@RequestBody @Valid UserLoginReqVO vo) {
        TUserEntity data = userService.login(vo);
        return new DataResult<>(data);
    }

}


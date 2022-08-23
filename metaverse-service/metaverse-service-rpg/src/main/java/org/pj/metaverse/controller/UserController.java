package org.pj.metaverse.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.util.MapUtils;
import de.huxhorn.sulky.ulid.ULID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TestEntity;
import org.pj.metaverse.entity.UserEntity;
import org.pj.metaverse.entity.reqvo.UserLoginReqVO;
import org.pj.metaverse.entity.reqvo.UserRegisterReqVO;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.repository.redis.TestRepositoryRedis;
import org.pj.metaverse.repository.redis.UserRepositoryRedis;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.UserService;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author pengjie
 * @date 16:26 2022/6/1
 **/
@RestController
@Api(tags = "测试")
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public DataResult<UserEntity> register(@RequestBody @Valid UserRegisterReqVO vo) {
        UserEntity data = userService.register(vo);
        return new DataResult<>(data);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public DataResult<UserEntity> login(@RequestBody @Valid UserLoginReqVO vo) {
        UserEntity data = userService.login(vo);
        return new DataResult<>(data);
    }


}

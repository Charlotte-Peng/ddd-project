package org.pj.metaverse.system.controller.feign;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.system.service.IBaseAvatarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * <p>
 * 系统通用头像 前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-17 16:36:21
 */
@Api(hidden = true)
@Controller
@RequestMapping("/feign/baseAvatar")
@RequiredArgsConstructor
public class BaseAvatarFeignController {
    private final IBaseAvatarService baseAvatarService;

    @GetMapping("random")
    public Set<String> randomlyObtainTheAvatarOfTheSpecifiedType(@RequestParam Integer type, Integer num){
       return baseAvatarService.randomlyObtainTheAvatarOfTheSpecifiedType(type,num);
    }
}


package org.pj.metaverse.system.controller.feign;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.system.service.IBaseNameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * <p>
 * 系统通用名称 前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-17 16:36:21
 */
@Api(hidden = true)
@Controller
@RequestMapping("/feign/baseName")
@RequiredArgsConstructor
public class BaseNameFeignController {
    private final IBaseNameService baseNameService;

    @GetMapping("random")
    public Set<String> randomlyObtainTheNameOfTheSpecifiedType(@RequestParam Integer type, Integer num){
       return baseNameService.randomlyObtainTheNameOfTheSpecifiedType(type,num);
    }
}


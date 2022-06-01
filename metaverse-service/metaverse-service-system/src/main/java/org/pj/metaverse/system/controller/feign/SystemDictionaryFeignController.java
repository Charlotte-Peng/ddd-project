package org.pj.metaverse.system.controller.feign;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.system.service.ISystemDictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-09 11:26:30
 */
@Api(hidden = true)
@Controller
@RequestMapping("feign/systemDictionary")
@RequiredArgsConstructor
public class SystemDictionaryFeignController {
    private final ISystemDictionaryService service;

    @ApiOperation(value = "获取对应的系统配置")
    @GetMapping
    public String getDictionary(@RequestParam String key){
        return service.getDictionary(key);
    }
}


package org.pj.metaverse.system.controller;


import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.result.DataResult;
import org.pj.metaverse.system.entity.SystemDictionaryEntity;
import org.pj.metaverse.system.service.ISystemDictionaryService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-09 11:26:30
 */
@Controller
@RequestMapping("/systemDictionary")
@RequiredArgsConstructor
public class SystemDictionaryController {
    private final ISystemDictionaryService service;

    @ApiOperation(value = "获取对应的系统配置")
    @GetMapping
    public DataResult<String> getDictionary(@RequestParam String key){
        String data = service.getDictionary(key);
        return new DataResult<>(DataResult.success(), data);
    }

    @ApiOperation(value = "添加系统配置")
    @PostMapping
    public DataResult<Void> addDictionary(@RequestBody SystemDictionaryEntity entity){
        service.addDictionary(entity);
        return DataResult.success();
    }

}


package org.pj.metaverse.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.SystemDictionaryEntity;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.ISystemDictionaryService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-09 11:26:30
 */
@RestController
@RequestMapping("/systemDictionary")
@RequiredArgsConstructor
@Api(tags = "系统配置")
public class SystemDictionaryController {
    private final ISystemDictionaryService service;

    @ApiOperation(value = "获取对应的系统配置")
    @GetMapping
    public DataResult<String> getDictionary(@RequestParam String key){
        String data = service.getDictionary(key);
        return DataResult.success(data);
    }

    @ApiOperation(value = "修改对应的系统配置")
    @PutMapping
    public DataResult<Void> updateDictionary(@RequestBody SystemDictionaryEntity entity){
        service.updateDictionary(entity);
        return DataResult.success();
    }

    @ApiOperation(value = "添加系统配置")
    @PostMapping
    public DataResult<Void> addDictionary(@RequestBody SystemDictionaryEntity entity){
        service.addDictionary(entity);
        return DataResult.success();
    }

}


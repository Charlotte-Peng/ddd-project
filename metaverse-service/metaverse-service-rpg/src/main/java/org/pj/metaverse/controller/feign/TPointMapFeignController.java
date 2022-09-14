package org.pj.metaverse.controller.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TPointMapEntity;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.ITPointMapService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengjie
 * @date 16:30 2022/9/13
 **/
@RestController
@Api(tags = "【服务内部】地图")
@RequiredArgsConstructor
@RequestMapping("feign/pointMap")
public class TPointMapFeignController {
    private final ITPointMapService pointMapService;

    @ApiOperation("查询地图详情")
    @PostMapping("queryMapDetail/{code}")
    public TPointMapEntity queryMapDetail(@PathVariable String code){
        return pointMapService.lambdaQuery()
                .eq(TPointMapEntity::getMapCode, code)
                .last("limit 1")
                .one();
    }
}

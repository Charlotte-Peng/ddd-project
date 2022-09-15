package org.pj.metaverse.controller.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TCityEntity;
import org.pj.metaverse.entity.TPointMapEntity;
import org.pj.metaverse.service.ITCityMgmtService;
import org.pj.metaverse.service.ITCityService;
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
@Api(tags = "【服务内部】城镇")
@RequiredArgsConstructor
@RequestMapping("feign/city")
public class TCityFeignController {
    private final ITCityService cityService;

    @ApiOperation("查询城镇详情")
    @PostMapping("queryCityDetail/{code}")
    public TCityEntity queryCityDetail(@PathVariable String code){
        return cityService.lambdaQuery()
                .eq(TCityEntity::getMapCode, code)
                .last("limit 1")
                .one();
    }
}

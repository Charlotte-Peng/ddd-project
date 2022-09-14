package org.pj.metaverse.controller.mgmt;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TPointMapEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateMapReqVO;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.ITPointMapMgmtService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pengjie
 * @date 9:22 2022/9/13
 **/
@RestController
@RequestMapping("mgmt/pointMap")
@Api(tags = "【后台】关卡管理")
@RequiredArgsConstructor
public class TPointMapMgmtController {

    private final ITPointMapMgmtService pointMapMgmtService;

    @ApiOperation("创建地图")
    @PostMapping("createMap")
    public DataResult<Void> createMap(@RequestBody MgmtCreateMapReqVO vo){
        pointMapMgmtService.createMap(vo);
        return DataResult.success();
    }

    @ApiOperation("查询地图列表")
    @PostMapping("queryMapList")
    public DataResult<IPage<TPointMapEntity>> queryMapList(HttpServletRequest request){
        IPage<TPointMapEntity> data = pointMapMgmtService.queryMapList(request.getParameterMap());
        return DataResult.success(data);
    }

    @ApiOperation("查询地图详情")
    @PostMapping("queryMapDetail/{id}")
    public DataResult<TPointMapEntity> queryMapDetail(@PathVariable Integer id){
        TPointMapEntity data = pointMapMgmtService.getById(id);
        return DataResult.success(data);
    }



}

package org.pj.metaverse.controller.mgmt;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TCityEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateCityReqVO;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.ITCityMgmtService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 城镇地图信息 前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@RestController
@RequestMapping("mgmt/city")
@Api(tags = "【后台】城镇地图信息")
@RequiredArgsConstructor
public class TCityMgmtController {
    private final ITCityMgmtService cityMgmtService;

    @ApiOperation("创建城镇地图信息")
    @PostMapping("createCity")
    public DataResult<Void> createCity(@RequestBody MgmtCreateCityReqVO vo) {
        cityMgmtService.createCity(vo);
        return DataResult.success();
    }

    @ApiModelProperty("查询城镇地图列表")
    @GetMapping("queryCityList")
    public DataResult<IPage<TCityEntity>> queryCityList(HttpServletRequest request) {
        IPage<TCityEntity> data = cityMgmtService.queryCityList(request.getParameterMap());
        return DataResult.success(data);
    }

    @ApiModelProperty("查询城镇地图详情")
    @PostMapping("queryCityDetail/{id}")
    public DataResult<TCityEntity> queryCityDetail(@PathVariable Integer id) {
        TCityEntity data = cityMgmtService.getById(id);
        return DataResult.success(data);
    }




}


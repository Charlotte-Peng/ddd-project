package org.pj.metaverse.controller.mgmt;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.reqvo.MgmtCreatePointInfoReqVO;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.ITPointInfoMgmtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 关卡节点相关信息 前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@Api(tags = "【后台】关卡节点相关信息")
@RestController
@RequestMapping("mgmt/pointInfo")
@RequiredArgsConstructor
public class TPointInfoMgmtController {
    private final ITPointInfoMgmtService pointInfoMgmtService;

    @ApiOperation("创建关卡节点信息")
    @PostMapping("createPointInfo")
    public DataResult<Void> createPointInfo(MgmtCreatePointInfoReqVO reqVO) {
        pointInfoMgmtService.createPointInfo(reqVO);
        return DataResult.success();
    }


}


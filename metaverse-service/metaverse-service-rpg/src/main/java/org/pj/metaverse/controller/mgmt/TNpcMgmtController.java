package org.pj.metaverse.controller.mgmt;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TNpcEntity;
import org.pj.metaverse.entity.reqvo.MgmtCreateNpcReqVO;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.ITNpcMgmtService;
import org.pj.metaverse.service.ITNpcService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * NPC信息表 前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-08-25 14:40:06
 */
@RestController
@RequestMapping("mgmt/npc")
@Api(tags = "【后台】NPC信息表")
@RequiredArgsConstructor
public class TNpcMgmtController {
    private final ITNpcMgmtService npcMgmtService;

    @ApiOperation("创建npc")
    @PostMapping("createNpc")
    public DataResult<Void> createNpc(@RequestBody MgmtCreateNpcReqVO vo) {
        npcMgmtService.createNpc(vo);
        return DataResult.success();
    }

    @ApiOperation("查询npc列表")
    @GetMapping("listNpc")
    public DataResult<IPage<TNpcEntity>> listNpc(HttpServletRequest request) {
        IPage<TNpcEntity> data = npcMgmtService.listNpc(request.getParameterMap());
        return DataResult.success(data);
    }

    @ApiOperation("查询npc详情")
    @GetMapping("getNpc/{id}")
    public DataResult<TNpcEntity> getNpc(@PathVariable("id") Integer id) {
        TNpcEntity data = npcMgmtService.getById(id);
        return DataResult.success(data);
    }


}


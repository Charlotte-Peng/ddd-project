package org.pj.metaverse.controller.mgmt;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.PermissionEntity;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.IPermissionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 权限接口表 前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Api(tags = "【后台】权限相关的控制器")
@RestController
@RequestMapping("mgmt/permission")
@RequiredArgsConstructor
public class MgmtPermissionController {
    private final IPermissionService permissionService;

    @ApiOperation(value = "添加权限")
    @PostMapping
    public DataResult<Void> addPermission(@RequestBody PermissionEntity entity){
        return permissionService.addPermission(entity);
    }

    @ApiOperation(value = "批量添加权限")
    @PostMapping("batch")
    public DataResult<Void> addBatchPermission(@RequestBody List<PermissionEntity> entity){
        return permissionService.addBatchPermission(entity);
    }

}


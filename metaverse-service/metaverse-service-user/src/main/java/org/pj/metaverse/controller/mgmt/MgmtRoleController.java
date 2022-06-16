package org.pj.metaverse.controller.mgmt;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.RoleEntity;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.service.IRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-10 11:21:46
 */
@Api(tags = "【后台】角色相关的控制器")
@RestController
@RequestMapping("/mgmt/role")
@RequiredArgsConstructor
public class MgmtRoleController {
    private final IRoleService roleService;

    @ApiOperation(value = "添加角色")
    @PostMapping
    public DataResult<Void> addRole(@RequestBody RoleEntity entity){
        return roleService.addRole(entity);
    }

    @ApiOperation(value = "批量添加角色")
    @PostMapping("batch")
    public DataResult<Void> addBatchRole(@RequestBody List<RoleEntity> entity){
        return roleService.addBatchRole(entity);
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public DataResult<Void> updateRole(@RequestBody RoleEntity entity){
        return roleService.updateRole(entity);
    }



}


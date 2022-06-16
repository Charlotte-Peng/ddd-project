package org.pj.metaverse.controller.feign;

import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.RoleEntity;
import org.pj.metaverse.service.IRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author pengjie
 * @date 11:22 2022/5/10
 **/
@ApiIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("feign/role")
public class RoleFeignController {
    private final IRoleService roleService;

    @GetMapping

    public List<RoleEntity> getRoleList(){
        return roleService.getRoleList();
    }
}

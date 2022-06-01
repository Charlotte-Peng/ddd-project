package org.pj.metaverse.user.controller.feign;

import lombok.RequiredArgsConstructor;
import org.pj.metaverse.user.entity.PermissionEntity;
import org.pj.metaverse.user.service.IPermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author pengjie
 * @date 17:23 2022/5/10
 **/
@ApiIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("feign/permission")
public class PermissionFeignController {
    private final IPermissionService permissionService;

    @GetMapping
    public List<PermissionEntity> getPermissionList(){
        return permissionService.getPermissionList();
    }
}

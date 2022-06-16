package org.pj.metaverse.controller.feign;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengjie
 * @date 9:40 2022/5/30
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("feign/user")
public class UserFeignController {
    private final IUserService userService;

    @SaCheckPermission(value = "user:lineCount")
    @ApiOperation(value = "在线用户数量")
    @GetMapping("/line/count")
    public Long getLineCount() {
        return userService.getLineCount();
    }
}

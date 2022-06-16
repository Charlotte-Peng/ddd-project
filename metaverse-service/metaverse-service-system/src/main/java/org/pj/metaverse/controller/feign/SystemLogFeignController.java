package org.pj.metaverse.controller.feign;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.service.ISystemLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengjie
 * @since 2022-05-09 11:26:30
 */
@Api(hidden = true)
@Controller
@RequestMapping("SystemLogFeign")
@RequiredArgsConstructor
public class SystemLogFeignController {
    private final ISystemLogService systemLogService;

    /*public DataResult<Void> writeOperationRecord(){

    }*/
}


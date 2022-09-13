package org.pj.metaverse.controller.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.entity.TUserLogEntity;
import org.pj.metaverse.service.ITUserLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengjie
 * @date 16:14 2022/9/13
 **/
@RestController
@Api(tags = "【服务内部】用户操作记录")
@RequiredArgsConstructor
@RequestMapping("feign/userLog")
public class TUserLogController {
    private final ITUserLogService userLogService;

    @ApiOperation("获取用户指定类型操作记录")
    @GetMapping("getLogByUserIdAndLogType")
    public TUserLogEntity getLogByUserIdAndLogType(String userId, Integer logType) {
        return userLogService.lambdaQuery()
                .eq(TUserLogEntity::getUserId, userId)
                .eq(TUserLogEntity::getLogType, logType)
                .last("limit 1")
                .one();
    }
}

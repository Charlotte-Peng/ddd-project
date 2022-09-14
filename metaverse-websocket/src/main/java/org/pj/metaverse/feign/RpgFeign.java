package org.pj.metaverse.feign;

import io.swagger.annotations.ApiOperation;
import org.pj.metaverse.entity.vo.TUserLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author pengjie
 * @date 16:03 2022/9/13
 **/
@FeignClient(name = "metaverse-service-rpg")
public interface RpgFeign {
    /**
     * 获取用户指定类型操作记录
     * @author pengjie
     * @date 2022/9/13 16:22
     * @param userId 用户id
     * @param logType 日志类型
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @GetMapping("feign/userLog/getLogByUserIdAndLogType")
    Map<String,Object> getLogByUserIdAndLogType(@RequestParam String userId, @RequestParam Integer logType);

    /**
     * 写入用户指定类型操作记录
     * @author pengjie
     * @date 2022/9/14 16:59
     * @param userLogEntity 用户操作记录
     */
    @PostMapping("feign/userLog/writeLogByUserIdAndLogType")
    void writeLogByUserIdAndLogType(@RequestBody TUserLogVO userLogEntity);

    /**
     * 查询地图详情
     * @author pengjie
     * @date 2022/9/13 16:54
     * @param code 地图code
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @PostMapping("feign/pointMap/queryMapDetail/{code}")
    Map<String,Object> queryMapDetail(@PathVariable String code);
}

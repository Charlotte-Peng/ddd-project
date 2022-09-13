package org.pj.metaverse.feign;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author pengjie
 * @date 16:03 2022/9/13
 **/
@FeignClient(name = "metaverse-service-rpg")
@RequestMapping("feign")
public interface RpgFeign {
    /**
     * 获取用户指定类型操作记录
     * @author pengjie
     * @date 2022/9/13 16:22
     * @param userId 用户id
     * @param logType 日志类型
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @GetMapping("userLog/getLogByUserIdAndLogType")
    Map<String,Object> getLogByUserIdAndLogType(String userId, Integer logType);

    /**
     * 查询地图详情
     * @author pengjie
     * @date 2022/9/13 16:54
     * @param code 地图code
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @PostMapping("queryMapDetail/{code}")
    Map<String,Object> queryMapDetail(@PathVariable String code);
}

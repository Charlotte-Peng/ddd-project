package org.pj.metaverse.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pj.metaverse.constant.redis.WebSocketRedisConstant;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.result.DataResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author pengjie
 * @date 17:03 2022/8/23
 **/
@Api(tags = "WebSocket相关接口")
@RestController
@RequestMapping("/websocket")
public class WebSocketController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("获取服务器websocket地址")
    @RequestMapping("/getWebsocketUrl")
    public DataResult<String> login(@RequestParam String username, @RequestParam String password, @RequestParam String type) {
        if (!WebSocketRedisConstant.Type.RPG.equals(type)) {
            throw new ServerException("登录类型错误");
        }
        String websocketIpAddress = stringRedisTemplate.opsForSet().randomMember(WebSocketRedisConstant.WEBSOCKET_RPG_TYPE_KEY);
        if (websocketIpAddress == null) {
            throw new ServerException("没有可用的websocket服务器");
        }
        // 查询服务器节点和地址，发送给用户
        String msg = "ws://%s";
        return new DataResult<>(String.format(msg, websocketIpAddress));
    }

}

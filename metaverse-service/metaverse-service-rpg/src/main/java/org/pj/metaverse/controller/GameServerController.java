package org.pj.metaverse.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.constant.redis.WebSocketRedisConstant;
import org.pj.metaverse.entity.repvo.GameServerListRepVO;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author pengjie
 * @date 17:49 2022/8/23
 **/
@Api(tags = "游戏服务器相关接口")
@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class GameServerController {
    private final StringRedisTemplate redisTemplate;

    @RequestMapping("/list")
    public DataResult<List<GameServerListRepVO>> list() {
        Set<String> members = redisTemplate.opsForSet().members(WebSocketRedisConstant.WEBSOCKET_RPG_TYPE_KEY);
        if (NvlUtils.isNull(members)) {
            throw new ServerException("没有游戏服务器");
        }
        String serverNameFormat = "服务器名称:%s";
        String serverAdderFormat = "ws://%s/websocket";
        List<GameServerListRepVO> collect = members.stream().map(item -> {
            GameServerListRepVO vo = new GameServerListRepVO();
            vo.setServerName(String.format(serverNameFormat, item.substring(item.length() - 2)));
            vo.setServerAdder(String.format(serverAdderFormat, item));
            return vo;
        }).collect(Collectors.toList());
        return new DataResult<>(collect);`
    }
}

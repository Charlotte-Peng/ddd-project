package org.pj.metaverse.task;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.result.MessageResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 11:55 2022/8/23
 **/
@Component
@Slf4j
public class TaskRpgService {
    /**
     * 定时推送消息
     * @author pengjie
     * @date 2022/8/23 11:59
     */
    public void pushMessage(ChannelHandlerContext channelHandlerContext, MessageResult messageRequest) {
        // 打印消息
        log.info("推送消息：{}", messageRequest);
        // 定时推送消息
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(LocalDateTime.now().toString()));
    }
}

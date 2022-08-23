package org.pj.metaverse.init;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.common.RedisConstant;
import org.pj.metaverse.result.MessageResult;
import org.pj.metaverse.task.TaskRpgService;

import java.time.LocalDateTime;

/**
 * @author pengjie
 * @date 11:53 2022/8/23
 **/
@Slf4j
@RequiredArgsConstructor
public class WebsocketRunnable implements Runnable {
    private TaskRpgService taskRpgService;

    private ChannelHandlerContext channelHandlerContext;

    private MessageResult messageRequest;

    public WebsocketRunnable(ChannelHandlerContext channelHandlerContext,MessageResult messageRequest) {
        this.channelHandlerContext = channelHandlerContext;
        this.messageRequest = messageRequest;
    }

    /**
     * 任务定时推送消息
     * @author pengjie
     * @date 2022/8/23 11:54
     */
    @Override
    public void run() {
        try {
            switch (messageRequest.getSocketMessageType()){
                case RedisConstant.Type.RPG:
                    taskRpgService.pushMessage(channelHandlerContext,messageRequest);
                    break;
                default:
                    log.error("消息类型不存在：{}",messageRequest.getSocketMessageType());
            }
        } catch (Exception e) {
            log.error("websocket服务器推送消息发生错误：",e);
        }
    }
}

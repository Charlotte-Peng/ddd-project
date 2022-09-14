package org.pj.metaverse.init;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.constant.redis.WebSocketRedisConstant;
import org.pj.metaverse.handle.GameTypeFactory;
import org.pj.metaverse.result.MessageReqResult;
import org.pj.metaverse.task.TaskRpgService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author pengjie
 * @date 11:53 2022/8/23
 **/
@Slf4j
public class WebsocketRunnable implements Runnable {
    private final GameTypeFactory gameTypeFactory;

    private final ChannelHandlerContext channelHandlerContext;

    private final MessageReqResult messageRequest;

    public WebsocketRunnable(ChannelHandlerContext channelHandlerContext, MessageReqResult messageRequest, GameTypeFactory gameTypeFactory) {
        this.channelHandlerContext = channelHandlerContext;
        this.messageRequest = messageRequest;
        this.gameTypeFactory = gameTypeFactory;
    }

    /**
     * 任务定时推送消息
     * @author pengjie
     * @date 2022/8/23 11:54
     */
    @Override
    public void run() {
        try {
            log.debug("websocket定时任务开始执行,当前时间:{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            log.debug("websocket定时任务开始执行,参数为:{}", messageRequest);
            switch (messageRequest.getSocketMessageType()){
                case WebSocketRedisConstant.Type.RPG:
                    ChannelPromise promise = channelHandlerContext.newPromise();
                    gameTypeFactory.getState(MessageTypeConstant.HEART_BEAT).handle(messageRequest, channelHandlerContext, promise);
                    break;
                default:
                    log.error("消息类型不存在：{}",messageRequest.getSocketMessageType());
            }
        } catch (Exception e) {
            log.error("websocket服务器推送消息发生错误：",e);
        }
    }
}

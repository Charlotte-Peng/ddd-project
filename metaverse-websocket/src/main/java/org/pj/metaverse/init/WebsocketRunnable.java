package org.pj.metaverse.init;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.constant.redis.WebSocketRedisConstant;
import org.pj.metaverse.result.MessageResult;
import org.pj.metaverse.task.TaskRpgService;

/**
 * @author pengjie
 * @date 11:53 2022/8/23
 **/
@Slf4j
public class WebsocketRunnable implements Runnable {
    private TaskRpgService taskRpgService;

    private ChannelHandlerContext channelHandlerContext;

    private MessageResult messageRequest;

    public WebsocketRunnable(ChannelHandlerContext channelHandlerContext,MessageResult messageRequest,TaskRpgService taskRpgService) {
        this.channelHandlerContext = channelHandlerContext;
        this.messageRequest = messageRequest;
        this.taskRpgService = taskRpgService;
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
                case WebSocketRedisConstant.Type.RPG:
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

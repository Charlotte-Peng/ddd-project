package org.pj.metaverse.handle;

import io.netty.channel.ChannelHandlerContext;
import org.pj.metaverse.result.MessageReqResult;

/**
 * @author pengjie
 * @date 14:30 2022/8/29
 **/
public interface GameTypeHandle {
    /**
     * 游戏根据不同类型消息的抽象方法
     * @author pengjie
     * @date 2022/8/29 14:31
     * @param messageRequest 消息请求
     * @param ctx 通道相关信息
     */
    void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx);
}

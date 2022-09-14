package org.pj.metaverse.init;

import com.alibaba.fastjson.JSON;
import io.netty.channel.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.constant.redis.WebSocketRedisConstant;
import org.pj.metaverse.handle.GameTypeFactory;
import org.pj.metaverse.handle.GameTypeHandleCommon;
import org.pj.metaverse.result.MessageRepResult;
import org.pj.metaverse.result.MessageReqResult;
import org.pj.metaverse.task.TaskRpgService;
import org.pj.metaverse.utlis.RedisWebsocketUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * websocket 入站处理器
 * @see ChannelHandler.Sharable 保证处理器，在整个生命周期中就是以单例的形式存在，方便统计客户端的在线数量
 * @author pengjie
 * @date 11:26 2022/8/23
 **/
@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Resource
    RedisWebsocketUtils redisWebsocketUtils;

    @Resource
    private TaskRpgService taskRpgService;

    @Resource
    private GameTypeFactory gameTypeFactory;

    /**
     * 通道map，存储channel，用于群发消息，以及统计客户端的在线数量
     */
    private static final Map<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();
    /**
     * 任务map，存储future，用于停止队列任务
     */
    private static final Map<String, Future<?>> FUTURE_MAP = new ConcurrentHashMap<>();
    /**
     * 存储channel的id和用户主键的映射，客户端保证用户主键传入的是唯一值
     */
    private static final Map<String, String> CLIENT_MAP = new ConcurrentHashMap<>();

    /**
     * 客户端发送给服务端的消息
     * @param ctx ChannelHandlerContext
     * @param msg TextWebSocketFrame
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {

        try {
            // 接受客户端发送的消息
            MessageReqResult messageRequest = JSON.parseObject(msg.text(), MessageReqResult.class);

            // 每个channel都有id，asLongText是全局channel唯一id
            String key = ctx.channel().id().asLongText();
            // 存储channel的id和用户的主键
            CLIENT_MAP.put(key, messageRequest.getUserId());
            String logMsg = "接收到客户端消息，消息id：%s，消息类型：%s，消息内容：%s,消息数据：%s";
            log.info(String.format(logMsg, messageRequest.getMessageId(), messageRequest.getMessageType(), messageRequest.getMessage(), messageRequest.getData()));
            if (!CHANNEL_MAP.containsKey(key)) {
                // 使用channel中的任务队列，做周期循环推送客户端消息
                Future<?> future = ctx.channel()
                        .eventLoop()
                        .scheduleAtFixedRate(
                                new WebsocketRunnable(ctx, messageRequest,gameTypeFactory),
                                0,
                                redisWebsocketUtils.getWebsocketTaskCycleTime(WebSocketRedisConstant.Type.RPG),
                                TimeUnit.SECONDS);
                // 存储客户端和服务的通信的Chanel
                CHANNEL_MAP.put(key, ctx.channel());
                // 存储每个channel中的future，保证每个channel中有一个定时任务在执行
                FUTURE_MAP.put(key, future);
            } else {

            }
            // 每次客户端和服务的主动通信，和服务端周期向客户端推送消息互不影响 解决问题一

            gameTypeFactory.getState(messageRequest.getMessageType()).handle(messageRequest, ctx);
        } catch (Exception e) {
            log.error("websocket服务器推送消息发生错误：", e);
        }

    }

    /**
     * 客户端连接时候的操作
     * @param ctx 客户端连接上的上下文
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("一个客户端连接......" + ctx.channel().remoteAddress() + Thread.currentThread().getName());
    }

    /**
     * 客户端掉线时的操作
     * @param ctx 客户端掉线的上下文
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        String key = ctx.channel().id().asLongText();
        // 移除通信过的channel
        CHANNEL_MAP.remove(key);
        // 移除和用户绑定的channel
        CLIENT_MAP.remove(key);
        // 关闭掉线客户端的future
        Optional.ofNullable(FUTURE_MAP.get(key)).ifPresent(future -> {
            future.cancel(true);
            FUTURE_MAP.remove(key);
        });
        // 移除redis中的用户信息
        redisWebsocketUtils.removeUser(key);
        log.info("一个客户端移除......" + ctx.channel().remoteAddress());
        // 关闭连接
        ctx.close();
    }

    /**
     * 发生异常时执行的操作
     * @param ctx 发生异常的上下文
     * @param cause 异常信息
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        String key = ctx.channel().id().asLongText();
        //移除通信过的channel
        CHANNEL_MAP.remove(key);
        //移除和用户绑定的channel
        CLIENT_MAP.remove(key);
        //移除定时任务
        Optional.ofNullable(FUTURE_MAP.get(key)).ifPresent(future -> {
            future.cancel(true);
            FUTURE_MAP.remove(key);
        });
        // 移除redis中的用户信息
        redisWebsocketUtils.removeUser(key);
        //关闭长连接
        ctx.close();
        log.info("异常发生 " + cause.getMessage());
    }

    public static Map<String, Channel> getChannelMap() {
        return CHANNEL_MAP;
    }

    public static Map<String, Future<?>> getFutureMap() {
        return FUTURE_MAP;
    }

    public static Map<String, String> getClientMap() {
        return CLIENT_MAP;
    }
}

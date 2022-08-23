package org.pj.metaverse.init;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.common.RedisConstant;
import org.pj.metaverse.utlis.IpAdderUtils;
import org.pj.metaverse.utlis.RedisWebsocketUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Websocket 初始化器
 * @author pengjie
 * @date 10:42 2022/8/23
 **/
@Slf4j
@Component
public class WebsocketInitialization {

    @Resource
    private WebsocketChannelInitializer websocketChannelInitializer;
    @Resource
    private RedisWebsocketUtils redisWebsocketUtils;

    /**
     * 随机端口
     * @date 2022/8/23 11:03
     */
    private final Integer port = (int) (Math.random() * 10000) + 10000;

    @Async
    public void init() throws InterruptedException {

        //bossGroup连接线程组，主要负责接受客户端连接，一般一个线程足矣
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //workerGroup工作线程组，主要负责网络IO读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //启动辅助类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //bootstrap绑定两个线程组
            serverBootstrap.group(bossGroup, workerGroup);
            //设置通道为NioChannel
            serverBootstrap.channel(NioServerSocketChannel.class);
            //可以对入站\出站事件进行日志记录，从而方便我们进行问题排查。
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            //设置自定义的通道初始化器，用于入站操作
            serverBootstrap.childHandler(websocketChannelInitializer);
            //启动服务器,本质是Java程序发起系统调用，然后内核底层起了一个处于监听状态的服务，生成一个文件描述符FD
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 获取本机ip地址
            String ipAddress = IpAdderUtils.getLocalIpAddress();
            // 启动完成，将端口号写入redis进行管理
            redisWebsocketUtils.saveWebsocketInfo(ipAddress,port.toString(), RedisConstant.WEBSOCKET_RPG_TYPE_KEY);
            //异步
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}

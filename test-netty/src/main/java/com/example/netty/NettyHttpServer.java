package com.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


/**
 * @author xboat date 2019-03-17
 */
public class NettyHttpServer {

    private int port;
    /**
     *  用来接收进来的连接
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    /**
     * 用来处理已经被接收的连接
     */
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public NettyHttpServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        System.out.println("启动端口：" + port);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    // 这里告诉Channel如何接收新的连接
                    .channel(NioServerSocketChannel.class)
                    .childHandler( new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpResponseEncoder());
                            // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                            ch.pipeline().addLast(new HttpRequestDecoder());
                            // 自定义处理类
                            ch.pipeline().addLast(new NettyHttpServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024);
                   // .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            // 等待服务器socket关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            //释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

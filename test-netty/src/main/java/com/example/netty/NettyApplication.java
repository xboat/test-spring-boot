package com.example.netty;

/**
 * @author xboat date 2019-03-17
 */
public class NettyApplication {

    public static void main(String[] args) {
        System.out.println("start-->netty");
        System.out.println("监听开始：端口8099");
        NettyHttpServer nettyHttpServer = new NettyHttpServer(8099);
        try {
            nettyHttpServer.run();
        } catch (Exception e) {
            System.out.println("NettyHttpServer 启动失败"+e.getMessage());
        }
    }
}

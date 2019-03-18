package com.example.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpHeaderNames.*;


/**
 * @author xboat date 2019-03-17
 */
public class NettyHttpServerHandler extends ChannelInboundHandlerAdapter {

    private static ThreadLocal<HttpRequest> threadLocal = new ThreadLocal<>();

    /**
     * 收到数据时调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpRequest req;
        if (msg instanceof HttpRequest) {
            req =(HttpRequest)msg;
            threadLocal.set(req);
        }
        if (msg instanceof HttpContent) {
            req = threadLocal.get();
            System.out.println("uri--->"+req.getUri());
            // 页面输出请求内容
            FullHttpResponse response = getFullHttpResponse("hello netty!<br/>"+req.getUri());
            ctx.write(response);
            ctx.flush();
            threadLocal.remove();
        }
    }

    private FullHttpResponse getFullHttpResponse(String responseContent) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(responseContent.getBytes()));
        response.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8")
                .set(CONTENT_LENGTH,response.content().readableBytes())
                .set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        return response;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete--->flush");
       // ctx.flush();
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        System.out.println("exceptionCaught--->"+new Exception(cause).getMessage());
        ctx.close();
    }
}


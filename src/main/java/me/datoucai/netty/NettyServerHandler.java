package me.datoucai.netty;

import io.netty.channel.*;

import java.net.InetAddress;
import java.util.Date;

/**
 * @author cc
 * @date 2018/11/29
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端已收到新连接" + System.currentTimeMillis());
        // 为新连接发送庆祝
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!/r/n");
        ctx.write("It is " + new Date() + " now./r/n");
        ctx.flush();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端收到消息: " + msg);
        String response;
        boolean close = false;
        if (msg == null || msg.equals("")) {
            response = "Please type something./r/n";
        } else if ("bye".equals(msg)) {
            response = "Have a good day!/r/n";
            close = true;
        } else {
            response = "Did you say '" + msg + "'?/r/n";
        }
        ChannelFuture future = ctx.write(response);
        if (close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("服务端收到消息: " + s);
        channelRead(channelHandlerContext, s);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}

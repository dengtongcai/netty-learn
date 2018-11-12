package me.datoucai.nio.channel;

import java.net.InetSocketAddress;

/**
 * 服务器端：接收客户端发送过来的消息并显示
 * 服务器把接收到的数据加上“echo from sever”返回
 */
public class ServerSocketChannelDemo {

    public static class TCPEchoServer implements Runnable {

        /*服务器地址*/
        private InetSocketAddress localAddress;

        public TCPEchoServer(int port) {
            this.localAddress = new InetSocketAddress(port);
        }

        @Override
        public void run() {

            //创建选择器

            //创建服务器通道

            //绑定监听服务器的端口，设置最大连接缓冲数

            //设置感兴趣事件：tcp连接



            //
        }
    }
}

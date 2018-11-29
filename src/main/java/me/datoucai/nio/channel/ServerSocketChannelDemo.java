package me.datoucai.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

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
            Selector selector = null;
            ServerSocketChannel socketChannel = null;

            /**
             * 建立连接
             */
            try {
                //创建选择器
                selector = Selector.open();
                //创建服务器通道
                socketChannel = ServerSocketChannel.open();

                //绑定监听服务器的端口，设置最大连接缓冲数
                socketChannel.bind(localAddress, 100);
                //设置感兴趣事件：tcp连接
                socketChannel.register(selector, SelectionKey.OP_ACCEPT);

            } catch (IOException e) {
                e.printStackTrace();
            }

            /**
             * 读取
             */
            while (!Thread.currentThread().isInterrupted()) {
                int select = 0;
                try {
                    select = selector.select();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (select == 0) continue;
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    try {
                        if (key.isAcceptable()) {
                            SocketChannel sc = socketChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_ACCEPT);

                            System.out.println("远程地址" + sc.getRemoteAddress());
                        }
                        //读取数据
                        if (key.isReadable()) {
                            key.attachment();
                            ByteBuffer readBuf = ByteBuffer.allocate(128);
                            ByteBuffer writeBuf = ByteBuffer.allocate(128);
                            SocketChannel channel = (SocketChannel) key.channel();

                            //读取
                            channel.read(readBuf);
                            readBuf.flip();


                            int len = 0;
                            while (writeBuf.hasRemaining()) {
                                len = channel.write(writeBuf);
                                if (len == 0) break;
                            }
                            writeBuf.compact();

                            //数据不为空，已经全部写入
                            if (len != 0) {
                                //取消通道
                                key.interestOps(key.interestOps() & SelectionKey.OP_WRITE);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                        try {
                            key.channel().close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

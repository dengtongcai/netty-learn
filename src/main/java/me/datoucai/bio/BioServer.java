package me.datoucai.bio;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class BioServer {
    private static int PORT = 8888;
    private static ServerSocket serverSocket;

    public static void start() {
        start(PORT);
    }

    private static synchronized void start(int port) {
        try {
            if (serverSocket == null) {
                serverSocket = new ServerSocket(port);
                log.info("服务器已启动，端口：{}", port);
            }
            String rec;
            for (; ; ) {
                log.info("等待接收消息...");
                @Cleanup Socket socket = serverSocket.accept();
                @Cleanup InputStream inputStream = socket.getInputStream();
                @Cleanup BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                if ((rec = in.readLine()) == null) break;
                log.info("服务端接收到消息：{}", rec);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

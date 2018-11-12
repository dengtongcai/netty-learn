package me.datoucai.bio;

public class SocketTest {
    public static void main(String[] args) {
        new Thread(()->{
            BioServer.start();
        }).start();

        new Thread(()->{
            BioClient.send("测试");
        }).start();

    }
}

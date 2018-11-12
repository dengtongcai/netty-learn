package me.datoucai.bio;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class BioClient {


    private static final String IP = "127.0.0.1";
    private static final int PORT = 8888;

    public static void send(String msg) {
        try {
            @Cleanup Socket socket = new Socket(IP, PORT);
            @Cleanup BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            @Cleanup PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(msg);
            out.println(("结果为：" + in.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

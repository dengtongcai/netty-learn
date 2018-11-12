package me.datoucai.nio.channel;

import lombok.Cleanup;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileChannelDemo {
    public static void readFile(String file) {
        try {
            Path path = Paths.get(file);
            @Cleanup FileChannel channel = FileChannel.open(path);

            Charset utf8 = Charset.forName("utf8");
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);

            channel.read(byteBuffer);
            byteBuffer.flip();
            CharBuffer charBuffer = utf8.decode(byteBuffer);

            System.out.println(charBuffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

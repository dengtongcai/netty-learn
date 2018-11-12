package me.datoucai.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class BufferDemo {
    /**
     * 解密
     */
    public static void decode(String msg) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put(msg.getBytes());
        byteBuffer.flip();

        Charset utf8 = Charset.forName("utf8");
        CharBuffer charBuffer = utf8.decode(byteBuffer);

        char[] chars = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
        System.out.println(chars);
    }

    /**
     * 加密
     */
    public static void encode(String msg) {
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.append(msg);
        charBuffer.flip();

        Charset utf8 = Charset.forName("utf8");
        ByteBuffer byteBuffer = utf8.encode(charBuffer);

        byte[] bytes = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
        System.out.println(Arrays.toString(bytes));

    }

}

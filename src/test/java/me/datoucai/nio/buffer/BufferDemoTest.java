package me.datoucai.nio.buffer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BufferDemoTest {

    @Test
    public void decode() {
        BufferDemo.decode("中文中文");
    }

    @Test
    public void encode() {
        BufferDemo.encode("中文中文");
    }
}

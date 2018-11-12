package me.datoucai.nio.channel;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileChannelDemoTest {

    @Test
    public void readFile() {
        FileChannelDemo.readFile("C:\\Users\\Administrator\\Desktop\\gupao.txt");
    }
}
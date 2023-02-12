package FileInOutputStream;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharSetDemo {
    @Test
    void testCharSet() throws UnsupportedEncodingException {
        String str = "AI机器人";
        byte[] bytesUTF8 = str.getBytes(); // IDEA默认UTF8
        System.out.println("UFT-8长度：" + bytesUTF8.length + "， 内容：" + Arrays.toString(bytesUTF8));
        System.out.println(new String(bytesUTF8)); // 默认解码UTF8

        // GBK编码解码
        byte[] bytesGBK = str.getBytes("GBK"); // GBK
        System.out.println("GBK长度：" + bytesGBK.length + "， 内容：" + Arrays.toString(bytesGBK));
        System.out.println(new String(bytesGBK, "GBK")); // 使用GBK解码
    }
}

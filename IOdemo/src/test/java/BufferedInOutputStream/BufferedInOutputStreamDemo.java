package BufferedInOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BufferedInOutputStreamDemo {

    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;

    @BeforeEach
    void init() throws FileNotFoundException {
        // 默认缓冲区 8192
        bis = new BufferedInputStream(new FileInputStream("src/test/resources/a.txt"));
        bos = new BufferedOutputStream(new FileOutputStream("src/test/resources/a_copy.txt"));
    }

    // 字节缓冲流一次读取一个字节
    @Test
    void copy() throws IOException {
        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        System.out.println("拷贝完毕");
    }

    // 字节缓冲流一次读取多个字节
    @Test
    void copyWithArr() throws IOException {
        int len;
        byte[] bytes = new byte[1024];
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        System.out.println("拷贝完毕");
    }

    @AfterAll
    void close() throws IOException {
        if (bis != null) {
            bis.close();
        }

        if (bos != null) {
            bos.close();
        }
    }

}

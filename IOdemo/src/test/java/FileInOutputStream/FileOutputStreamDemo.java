package FileInOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileOutputStreamDemo {

    String fileName = "./test/FileOutputStreamDemo.txt";
    FileOutputStream fos = null;

    @BeforeAll
    void getOutputStream() throws FileNotFoundException {
        fos = new FileOutputStream(fileName);
        System.out.println("打开文件流");
    }

    // 以追加方式打开文件
    private void getAppendOutputStream() throws FileNotFoundException {
        fos = new FileOutputStream(fileName, true);
        System.out.println("追加方式打开文件输出流");
    }

    @Test
    void testOutputByte() {
        // 一次写一个byte
        try {
            fos.write(97); //a
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testOutputBytes() {
        // 一次写一个byte数组
        byte[] bytes = {65, 66, 67, 68, 69, 70};
        try {
            // fos.write(bytes); // ABCDEF
            fos.write(bytes, 1, 2); // BC
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testOutputString() {
        // 换行写
        String s = "Hello\r\nWorld";
        try {
            fos.write(s.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    void closeOutputStream() {
        if (fos != null) {
            try {
                fos.close();
                System.out.println("关闭文件流");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


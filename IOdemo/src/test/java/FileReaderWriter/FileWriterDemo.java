package FileReaderWriter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileWriter;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileWriterDemo {
    // 字符输出流也有8k缓冲区，如果缓冲区没有满需要用flush()刷新缓冲区

    String fileName = "./test/FileWriterDemo.txt";
    FileWriter fw = null;

    @BeforeAll
    void getFileWriter() throws IOException {
        fw = new FileWriter(fileName);
        System.out.println("打开字符输出流");
    }

    // 以追击方式
    private void getAppendFileWriter() throws IOException {
        fw = new FileWriter(fileName, true);
        System.out.println("追加方式打开字符输出流");
    }

    @Test
    void testFileWriteByte() {
        // 一次写一个byte
        try {
            fw.write(97); //a
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFileWriteStr() {
        String str = "你好世界";
        try {
//            fw.write(str);
            fw.write(str, 0, 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    void closeOutputStream() {
        if (fw != null) {
            try {
                fw.close();
                System.out.println("关闭字符输出流");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

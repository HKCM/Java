package FileInOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileInputStreamDemo {

    String fileName = "src/test/resources/a.txt";
    FileInputStream fis = null;

    @BeforeAll
    void getInputStream() throws FileNotFoundException {
        fis = new FileInputStream(fileName);
        System.out.println("打开文件流");
    }

    // 循环读取
    @Test
    void test() throws IOException {
        int b;
        while ((b = fis.read()) != -1) {
            System.out.print((char) b);
        }
        System.out.println("文件读取完成");
    }

    @AfterAll
    void closeInputStream() {
        if (fis != null) {
            try {
                fis.close();
                System.out.println("关闭文件流");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package FileReaderWriter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileReaderDemo {
// 字符输入流会有8k的缓冲区

    String fileName = "src/test/resources/a.txt";
    FileReader fr = null;

    @BeforeAll
    void getFileReader() throws FileNotFoundException {
        fr = new FileReader(fileName);
        System.out.println("打开字符输入流");
        System.out.println("------------");
    }

    // 循环读取
    @Test
    void testSingleFilerRead() throws IOException {
        int b;
        while ((b = fr.read()) != -1) {
            System.out.print((char) b);
        }
        System.out.println("\r\n------------");
        System.out.println("文件读取完成");
    }

    // 数组循环读取
    @Test
    void testArrFilerRead() throws IOException {
        char[] b = new char[2];
        int len = 0;
        while ((len = fr.read(b)) != -1) {
            System.out.print(new String(b, 0, len));
        }
        System.out.println("\r\n------------");
        System.out.println("文件读取完成");
    }

    @AfterAll
    void closeFileReader() {
        if (fr != null) {
            try {
                fr.close();
                System.out.println("------------");
                System.out.println("关闭字符输出流");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package FileInOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileCopyDemo {
    String inputFileName = "src/test/resources/a.txt";
    String outputFileName = "src/test/resources/a.txt.copy";

    FileInputStream fis = null;
    FileOutputStream fos = null;

    // 单字符拷贝 速度慢
    @Test
    public void singleByteFileCopy() throws IOException {
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        System.out.println("文件拷贝完成");
    }


    // 多字符拷贝 速度慢
    @Test
    public void bufferBytesFileCopy() throws IOException {
        int len = 0;
        byte[] b = new byte[3];
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        System.out.println("文件拷贝完成");
    }

    @BeforeAll
    void getInputStream() throws FileNotFoundException {
        fis = new FileInputStream(inputFileName);
        System.out.println("打开文件输入流");
        fos = new FileOutputStream(outputFileName);
        System.out.println("打开文件输出流");
    }

    @AfterAll
    void closeInputStream() {
        // 先开的后关
        if (fos != null) {
            try {
                fos.close();
                System.out.println("关闭文件输出流");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fis != null) {
            try {
                fis.close();
                System.out.println("关闭文件输入流");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

package ConvertStream;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;

public class ConvertStreamDemo {

    private OutputStreamWriter getOutputStreamWriter() throws FileNotFoundException {
        // 以指定字符集输出数据
        // 使用特定字符集写出数据
        OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream("src/test/resources/gbkfile.txt"), Charset.forName("GBK")
        );
        return osw;
    }

    private InputStreamReader getInputStreamReader() throws FileNotFoundException {
        // 以指定字符集读取数据
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream("src/test/resources/gbkfile.txt"), Charset.forName("GBK")
        );
        return isr;
    }

    @Test
    void createGBKFile() throws IOException {
        OutputStreamWriter osw = getOutputStreamWriter();
        String msg = "你好世界";
        osw.write(msg);
        closeOutputStream(osw);
    }

    @Test
    void readGBKFile() throws IOException {
        InputStreamReader isr = getInputStreamReader();
        int b;
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);
        }

        closeInputStream(isr);
    }

    private void closeInputStream(InputStreamReader isr) throws IOException {
        if (isr != null) {
            isr.close();
        }
    }

    private void closeOutputStream(OutputStreamWriter osw) throws IOException {
        if (osw != null) {
            osw.close();
        }
    }


}

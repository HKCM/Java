package BufferedInOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BufferedReadWriter {
    BufferedReader br = null;
    BufferedWriter bw = null;

    @BeforeEach
    void init() throws IOException {
        // 默认缓冲区 8192
        br = new BufferedReader(new FileReader("src/test/resources/a.txt"));
        bw = new BufferedWriter(new FileWriter("src/test/resources/a_copy.txt"));
    }


    // 字符缓冲流
    @Test
    void copyWithArr() throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        System.out.println("拷贝完毕");
    }

    @AfterAll
    void close() throws IOException {
        if (br != null) {
            br.close();
        }

        if (bw != null) {
            bw.close();
        }
    }
}

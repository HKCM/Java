package FileInOutputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Encrypt {
    File normalFile;
    File encryptedFile;
    File decryptedFile;
    int key = 502;

    @BeforeAll
    void init() {
        normalFile = new File("src/test/resources/a.txt");
        encryptedFile = new File("src/test/resources/a_encrypted.txt");
        decryptedFile = new File("src/test/resources/a_decrypted.txt");
    }

    @Test
    void Encrypt() throws IOException {
        FileInputStream fis = new FileInputStream(normalFile);
        FileOutputStream fos = new FileOutputStream(encryptedFile);
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b ^ key);
        }

        fos.close();
        fis.close();
    }

    @Test
    void Decrypt() throws IOException {
        FileInputStream fis = new FileInputStream(encryptedFile);
        FileOutputStream fos = new FileOutputStream(decryptedFile);
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b ^ key);
        }

        fos.close();
        fis.close();
    }
}

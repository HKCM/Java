package Folders;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FolderCopyDemo {

    String prefix = "test";
    String sourceFolder = prefix;
    String DestinationFolder = "testCopy";

    @Test
    public void createMoreFiles() throws IOException {
        System.out.println("创建测试文件夹");
        Files.createDirectories(Paths.get(prefix + "/test1/test2/test3/test4/test5/"));
        Files.write(Paths.get(prefix + "/test1/test2/test2.log"), "hello".getBytes());
        Files.write(Paths.get(prefix + "/test1/test2/test3/test3.log"), "hello".getBytes());
    }

    @Test
    void testCopyDir() throws IOException {
        File source = new File(prefix);
        File dest = new File("testCopy");
        copyDir(source, dest);
    }


    public void copyDir(File source, File dest) throws IOException {
        // 如果目的地目录不存在则创建
        dest.mkdirs();

        File[] files = source.listFiles();

        if (files == null) {
            System.out.println("无权限拷贝");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                // 如果是文件,拷贝
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(new File(dest, file.getName()));
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = fis.read(b)) != -1) {
                    fos.write(b, 0, len);
                }
                fos.close();
                fis.close();
            } else {
                // 如果是文件夹，递归
                copyDir(file, new File(dest, file.getName()));
            }
        }

    }

}

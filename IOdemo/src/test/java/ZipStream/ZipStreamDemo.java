package ZipStream;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipStreamDemo {

    @Test
    void testUnZip() throws IOException {
        File src = new File("src/test/resources/ziptest.zip");
        File dest = new File("src/test/resources/");

        ZipInputStream zis = new ZipInputStream(new FileInputStream(src));

        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            System.out.println(entry.getName());
            int pos = entry.getName().lastIndexOf("/");
            if (pos != -1) {
                String dir = entry.getName().substring(0, pos);
                File file = new File(dest, dir);
                file.mkdirs();
                System.out.println(dir);
            }

            FileOutputStream fos = new FileOutputStream(new File(dest, entry.toString()));
            int len;
            byte[] bytes = new byte[1024 * 10];
            while ((len = zis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.close();

            // 代表压缩包中的一个entry处理完毕
            zis.closeEntry();
        }
        zis.close();
        System.out.println("解压完毕");
    }


    // 压缩单个文件
    @Test
    void zipSingleFile() throws IOException {
        File src = new File("src/test/resources/a.txt");
        File dest = new File("src/test/resources/a.zip");

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));
        // 创建ZipEntry对象，表示压缩包里面的每一个文件和文件夹
        ZipEntry entry = new ZipEntry("a.txt");

        // 把ZipEntry对象放到压缩包当中
        zos.putNextEntry(entry);

        // 把src中的数据放入entry
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while ((len = bis.read(bytes)) != -1) {
            zos.write(bytes, 0, len);
        }
        System.out.println("压缩完成");
        bis.close();
        // 先关闭entry对象
        zos.closeEntry();
        zos.close();
    }

    // 压缩文件夹
    @Test
    void zipFolders() throws IOException {
        File src = new File("src/test/resources/ziptest");
        File dest = new File(src.getParent(), src.getName() + ".zip");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));
        // 循环遍历每个文件
        toZip(src, zos, src.getName());

        System.out.println("压缩完成");
        zos.close();
    }

    private void toZip(File src, ZipOutputStream zos, String name) throws IOException {
        File[] files = src.listFiles();
        if (files.length == 0) {
            // 添加空文件夹
            ZipEntry entry = new ZipEntry(name + "/");
            zos.putNextEntry(entry);
            zos.closeEntry();
        }
        for (File file : files) {
            System.out.println(file.getName());
            if (file.isFile()) {
                // 如果是文件
                // 创建ZipEntry对象，表示压缩包里面的每一个文件和文件夹
                ZipEntry entry = new ZipEntry(name + "/" + file.getName());

                // 把ZipEntry对象放到压缩包当中
                zos.putNextEntry(entry);

                // 把src中的数据放入entry
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                byte[] bytes = new byte[1024 * 8];
                int len = 0;
                while ((len = bis.read(bytes)) != -1) {
                    zos.write(bytes, 0, len);
                }
                bis.close();
                zos.closeEntry();
            } else {
                // 如果是文件夹
                // 递归调用
                toZip(file, zos, name + "/" + file.getName());
            }

        }
    }
}

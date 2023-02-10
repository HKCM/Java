package Folders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;

public class TestDeleteFolder {
    String prefix = "test";

    // 创建测试文件和文件夹
    @BeforeEach
    public void createMoreFiles() throws IOException {
        System.out.println("创建测试文件夹");
        Files.createDirectories(Paths.get(prefix + "/test1/test2/test3/test4/test5/"));
        Files.write(Paths.get(prefix + "/test1/test2/test2.log"), "hello".getBytes());
        Files.write(Paths.get(prefix + "/test1/test2/test3/test3.log"), "hello".getBytes());
    }

    //false只能告诉你失败了 ，但是没有给出任何失败的原因
    @Test
    public void testDeleteFileDir1() {
        File file = new File(prefix + "");
        boolean deleted = file.delete();
        System.out.println(deleted);
    }

    //void,删除失败没有任何提示，应避免使用这个方法，就是个坑
    @Test
    public void testDeleteFileDir2() {
        File file = new File(prefix + "");
        file.deleteOnExit();
    }

    //如果文件不存在，抛出NoSuchFileException
    //如果文件夹里面包含文件，抛出DirectoryNotEmptyException
    @Test
    public void testDeleteFileDir3() {
        Path path = Paths.get(prefix + "");
        try {
            Files.delete(path);   //返回值void
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件夹删除失败，测试通过");
        }
    }

    //如果文件不存在，返回false，表示删除失败(文件不存在)
    //如果文件夹里面包含文件，抛出DirectoryNotEmptyException
    @Test
    public void testDeleteFileDir4() throws IOException {
        Path path = Paths.get(prefix + "");
        boolean result = false;
        try {
            result = Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件夹删除失败");
        }
        System.out.println("文件夹删除结果：" + result);
    }

    // 遍历删除所有文件和文件夹
    @Test
    public void testDeleteFileDir5() throws IOException {
        // 删除test/test1/test2/*
        Path path = Paths.get(prefix + "/test1/test2");

        Files.walkFileTree(path,
                new SimpleFileVisitor<Path>() {
                    // 先去遍历删除文件
                    @Override
                    public FileVisitResult visitFile(Path file,
                                                     BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        System.out.printf("文件被删除 : %s%n", file);
                        return FileVisitResult.CONTINUE;
                    }

                    // 再去遍历删除目录
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir,
                                                              IOException exc) throws IOException {
                        Files.delete(dir);
                        System.out.printf("文件夹被删除: %s%n", dir);
                        return FileVisitResult.CONTINUE;
                    }
                }
        );
    }

    //
    @Test
    public void testDeleteFileDir6() throws IOException {
        Path path = Paths.get(prefix + "/test1");
        try (Stream<Path> walk = Files.walk(path)) {
            walk.sorted(Comparator.reverseOrder())
                    .forEach(DeleteFileDir -> {
                        try {
                            Files.delete(DeleteFileDir);
                            System.out.printf("删除文件成功：%s%n", DeleteFileDir);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.printf("删除文件失败：%s%n", DeleteFileDir);
                        }
                    });
        }
    }

    private void deleteDirectoryStream(Path path) {
        try {
            Files.delete(path);
            System.out.printf("删除文件成功：%s%n", path);
        } catch (IOException e) {
            System.err.printf("无法删除的路径 %s%n%s", path, e);
        }
    }

    // IO遍历删除
    @Test
    public void testDeleteFileDir7() {
        File file = new File(prefix + "/test1");
        deleteDirectoryLegacyIO(file);
    }

    private void deleteDirectoryLegacyIO(File file) {
        File[] list = file.listFiles();  //无法做到list多层文件夹数据
        if (list != null) {
            for (File temp : list) {     //先去递归删除子文件夹及子文件
                deleteDirectoryLegacyIO(temp);   //注意这里是递归调用
            }
        }
        if (file.delete()) {     //再删除自己本身的文件夹
            System.out.printf("删除成功 : %s%n", file);
        } else {
            System.err.printf("删除失败 : %s%n", file);
        }
    }
}

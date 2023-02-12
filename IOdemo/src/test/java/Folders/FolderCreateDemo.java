package Folders;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FolderCreateDemo {
    @Test
    public void testCreateFolder() {
        createFolder("test/aaa");
    }

    @Test
    public void testCreateFolders() {
        createFolders("test/bbb/bbb");
    }

    @Test
    public void testCreateFile() throws IOException {
//        createFile("test/ccc/ccc.txt"); // 父目录不存在则失败
        createFolders("test/ccc");
        createFile("test/ccc/ccc.txt");
    }

    // 创建文件夹,父目录不存在则不创建
    private void createFolder(String path) {
        if (path == null) throw new RuntimeException("文件目录为空");
        File folders = new File(path);
        if (folders.exists()) throw new RuntimeException("文件夹已存在");
        if (folders.mkdir()) {
            System.out.println(
                    "文件夹创建成功！目录为：" + folders.getPath() +
                            ", 上级目录为：" + folders.getAbsolutePath());
        } else {
            System.out.println("文件夹创建失败！父目录：" + folders.getParent());
        }
    }

    // 创建多层文件夹，如果父目录不存在则创建
    private void createFolders(String foldersPath) {
        if (foldersPath.equals("")) throw new RuntimeException("文件为空");
        File folders = new File(foldersPath);
        if (folders.exists()) throw new RuntimeException("文件目录已存在");
        if (folders.mkdirs()) {
            System.out.println(
                    "单文件夹创建成功！目录为：" + folders.getPath() +
                            ", 上级目录为：" + folders.getParent());
        } else {
            System.out.println("单文件夹创建失败！父目录：" + folders.getParent());
        }
    }

    // 创建单个文件, 上级
    private void createFile(String targetFile) throws IOException {
        if (targetFile.equals("")) throw new RuntimeException("文件名为空");
        // 创建文件
        File file = new File(targetFile);
        if (file.exists()) throw new RuntimeException("文件已存在");
        boolean result = file.createNewFile();
        if (result) {
            System.out.println(
                    "文件创建成功！路径为：" + file.getAbsolutePath() +
                            "上级目录为：" + file.getParent());
        }
    }
}

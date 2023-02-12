package ObjectStream;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectInOutputStreamDemo {

    private Stu getStu(){
        Stu stu = new Stu("张三",12);
        return stu;
    }

    // 序列化流
    @Test
    void testSerializable() throws IOException {
        Stu stu = getStu();
        // 2.创建序列化流的对象/对象操作输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/test/resources/stu.obj"));
        // 3.写出数据
        oos.writeObject(stu);
        // 4.释放资源
        oos.close();
    }

    // 序列化流:一次序列化多个对象
    @Test
    void testMultipleSerializable() throws IOException {

        Stu stu1 = new Stu("张三",12);
        Stu stu2 = new Stu("李四",13);
        Stu stu3 = new Stu("王武",14);
        List<Stu> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);
        // 2.创建序列化流的对象/对象操作输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/test/resources/stu.obj"));
        // 3.写出数据
        oos.writeObject(stus);
        // 4.释放资源
        oos.close();
    }

    // 一次反序列化多个对象
    @Test
    void testMultipleObjectInputStream() throws IOException, ClassNotFoundException {

        // 2.创建序列化流的对象/对象操作输出流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/resources/stu.obj"));
        // 3.写出数据
        ArrayList<Stu> stus = (ArrayList<Stu>) ois.readObject(); // EOFException

        for (Stu stu : stus) {
            System.out.println(stu);
        }
        // 4.释放资源
        ois.close();
    }

    // 反序列化
    @Test
    void testObjectInputStream() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test/resources/stu.obj"));
        Stu o = (Stu) ois.readObject();
        System.out.println(o);
        ois.close();
    }

    HashMap<String, Stu> hm = new HashMap<>();

}

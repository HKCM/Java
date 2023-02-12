package ObjectStream;

import java.io.Serializable;

public class Stu implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    // 该字段不会被序列化
    private transient String address;

    public Stu(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}



import com.jingluo.util.string.ToStringUtils;
import com.jingluo.util.bean.BeanCopier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 详细介绍类的情况.
 *
 * @ClassName BeanCopierTest
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public class BeanCopierTest {
    public static void main(String[] args) {
        Student student = new Student("1", "张三");
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Student(String.valueOf(i),"张三"+i));
        }
        Teacher teacher = BeanCopier.convertOne(student, Teacher.class);
        Collection<Teacher> teachers = BeanCopier.convertCollection(list, Teacher.class);
        System.out.println(ToStringUtils.toString(student));
        System.out.println(ToStringUtils.toString(teacher));
        teachers.forEach(System.out::println);
        System.out.println(student.hashCode() == teacher.hashCode());
    }
    public static class Student{
        private String age;
        public String name;
        public Teacher teacher;
        public Float kk;

        public Student(String age, String name) {
            this.age = age;
            this.name = name;
        }

        public Student() {

        }
    }
    public static class Teacher{
        private int age;
        private String name;

        public Date birthDay;
        public Teacher(int age, String name) {
            this.age = age;
            this.name = name;
        }
        public Teacher() {

        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", birthDay=" + birthDay +
                    '}';
        }
    }
}


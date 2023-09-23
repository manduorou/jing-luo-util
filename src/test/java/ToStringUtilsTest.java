

import com.jingluo.util.ot.string.ToStringUtils;

import java.util.Date;

/**
 *
 * @ClassName ToStringUtilsTest
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public class ToStringUtilsTest {
    public static void main(String[] args) {
        int num = 1;
        BeanCopierTest.Student student = new BeanCopierTest.Student();
        System.out.println(ToStringUtils.toString(num));
        //测试包装类
        System.out.println(student instanceof Object);
        System.out.println("1" instanceof Object);
        ToStringUtils.toStringAndPrint(1);
        ToStringUtils.toStringAndPrint(1D);
        ToStringUtils.toStringAndPrint(1L);
        ToStringUtils.toStringAndPrint(student);
        ToStringUtils.toStringAndPrint(new Date());
    }
}


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 详细介绍类的情况.
 *
 * @ClassName OtherTest
 * @Author oldTree
 * @Date 2023/8/27
 * @Version 1.0
 */
public class OtherTest {
    public static void main(String[] args) {
        System.out.println(args.getClass().getSimpleName());
        System.out.println(args.getClass().getName());
        System.out.println(args.getClass().getTypeName());
    }

}


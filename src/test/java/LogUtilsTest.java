import com.jingluo.util.ot.logger.Console;
import com.jingluo.util.ot.string.ToStringUtils;

/**
 * 详细介绍类的情况.
 *
 * @ClassName LogUtils
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public class LogUtilsTest {
    public static void main(String[] args) {
        Console.info("1234");
        Console.error("1234");
        Console.debug(ToStringUtils.toString(new BeanCopierTest.Student("","")));
        Console.fatal("1234");
    }
}



import com.jingluo.util.ot.string.ToStringUtils;
import com.jingluo.util.ot.bean.Result;
/**
 * 详细介绍类的情况.
 *
 * @ClassName HttpCodeTest
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public class HttpCodeTest{
    public static void main(String[] args) {
        Result<Object> result = Result.success()
                .data("李四").msg("123沙发上自习");
        System.out.println(ToStringUtils.toString(result));
        System.out.println(result.getData().getClass().getName());
    }
}



import com.jingluo.util.bean.RestResult;
import com.jingluo.util.json.JLJson;

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
        RestResult restResult = RestResult.normalResult();
        System.out.println(JLJson.parseObjToJson(restResult));
    }
}


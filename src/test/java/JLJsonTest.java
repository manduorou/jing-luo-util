import com.jingluo.util.ot.json.JLJson;
import com.jingluo.util.ot.logger.Console;

import java.util.HashMap;

/**
 * 详细介绍类的情况.
 *
 * @ClassName JJSonTest
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public class JLJsonTest {
    public static void main(String[] args) {
        String json = "\\{\"name\"" + ":\"1234\"," + "\"age\"" + ":\"1234\"" +
                "\\}";
        System.out.println(JLJson.formatJson(json));
        JLJson.toBean(null);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "张三");
        hashMap.put("age", 198);
        hashMap.put("status", false);
        System.out.println(JLJson.parseObjToJson(hashMap));
    }
}


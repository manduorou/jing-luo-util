import com.jingluo.util.idBuilder.IDBuilder;
import com.jingluo.util.idBuilder.SnowflakeIdWorker;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 详细介绍类的情况.
 *
 * @ClassName IdBuilderTest
 * @Author oldTree
 * @Date 2023/8/26
 * @Version 1.0
 */
public class IdBuilderTest {
    public static void main(String[] args) {
        new SnowflakeIdWorker(0L, 0L);
        long timeA = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("=====开始=====" + timeA);
        long timeB = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("=====结束=====" + timeB);
        System.out.println("=======耗时========" + (timeB - timeA));
        System.out.println(IDBuilder.snowId());
    }
}


import com.jingluo.util.string.Validator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 详细介绍类的情况.
 *
 * @ClassName ValidatorTest
 * @Author oldTree
 * @Date 2023/8/26
 * @Version 1.0
 */
public class ValidatorTest {
    public static void main(String[] args) {
        System.out.println(Validator.validCharSeqIsEmpty(""));
        System.out.println(Validator.validAndEqualsCharSeq("12","12"));
        System.out.println(Validator.validAndEqualsCharSeq(null,"23"));
        System.out.println(Validator.validMapIsEmpty(new HashMap<String,String>()));
        System.out.println(Validator.validCollectionIsEmpty(new ArrayList<>()));
    }
}


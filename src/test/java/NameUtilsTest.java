import com.jingluo.util.NameUtils;

/**
 * 详细介绍类的情况.
 *
 * @ClassName NameUtilsTest
 * @Author oldTree
 * @Date 2023/8/25
 * @Version 1.0
 */
public class NameUtilsTest {
    public static void main(String[] args) {
        //下划线转驼峰
        System.out.println(NameUtils.toCamelCase("simple_xi_zz"));
        //下划线转驼峰
        System.out.println(NameUtils.convertToCamelCase("ppx_aa_zz"));
        //驼峰转下划线
        System.out.println(NameUtils.toUnderLineNameStrict("AxPP"));
        //驼峰转下划线
        System.out.println(NameUtils.toUnderLineNameStrict("axPiPP"));
    }
}


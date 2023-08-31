import com.jingluo.util.ot.encrypt.DESEncoding;
import com.jingluo.util.ot.encrypt.EncryptEncoding;

/**
 * 详细介绍类的情况.
 *
 * @ClassName EncrypyEncodingTest
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public class EncryptEncodingTest {
    public static void main(String[] args) throws Exception {
        String source = "helloittx";
        System.out.println("原文: " + source);
        String key = "A1B2C3D4E5F60708";
        String encryptData = EncryptEncoding.DESEncryptEncoding(source, key);
        System.out.println("加密后: " + encryptData);
        String decryptData = EncryptEncoding.DESDecrypt(encryptData, key);
        System.out.println("解密后: " + decryptData);
    }
}


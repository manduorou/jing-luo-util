import com.jingluo.util.ot.encrypt.Base64Utils;

/**
 * 详细介绍类的情况.
 *
 * @ClassName Base64UtilsTest
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public class Base64UtilsTest {
    /*** @param args* @throws UnsupportedEncodingException */
    public static void main(String[] args) throws Exception {
        String strSrc = "Man";
        String strOut = Base64Utils.strToBase64Str(strSrc);
        System.out.println("源字符串 " + strSrc + " 的Base64码是：" + strOut);
        String strOut2 = Base64Utils.base64StrToStr(strOut);
        System.out.println("Base64码 " + strOut + " 的对应源字符串为：" + strOut2);
        byte[] inByteArray = {'a', 'b', 'c'};
        String base64Str = Base64Utils.byteArrayToBase64Str(inByteArray);
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < inByteArray.length; i++) {
            sb.append(inByteArray[i] + " ");
        }
        sb.append(']');
        System.out.println("字节数组：" + sb + " 的Base64码是：" + base64Str);
        byte[] outByteArray = Base64Utils.base64StrToByteArray(base64Str);
        StringBuilder sb2 = new StringBuilder();
        sb2.append('[');
        for (int i = 0; i < outByteArray.length; i++) {
            sb2.append(outByteArray[i] + " ");
        }
        sb2.append(']');
        System.out.println("Base64码为" + base64Str + " 的对应字节数组为：" + sb2);
    }
}


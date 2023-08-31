package com.jingluo.util.ot.encrypt;

/**
 * 加密编码接口
 * @ClassName EncryptEncoding
 * @Author oldTree
 * @Date 2023/8/29
 * @Version 1.0
 */
public interface EncryptEncoding {
    String encrypt(String data, String key);//加密
    String decrypt(String data, String key);//解密

    static String  DESEncryptEncoding(String data,String key) {
        try {
            return DESEncoding.encrypt(data, key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static String DESDecrypt (String data,String key){
        try {
            return DESEncoding.decrypt(data,key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

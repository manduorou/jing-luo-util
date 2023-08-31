package com.jingluo.util.ot.string;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 封装校验器
 * @ClassName Validator
 * @Author oldTree
 * @Date 2023/8/26
 * @Version 1.0
 */
public class Validator {
    public static <T> boolean validIsNull(T data){
        return data == null;
    }
    public static boolean validAndEqualsCharSeq(CharSequence charSequence1,CharSequence charSequence2) {
        return !validCharSeqIsEmpty(charSequence1) &&
                !validCharSeqIsEmpty(charSequence2) &&
                charSequence1.equals(charSequence2);
    }
    public static boolean validCharSeqIsEmpty(CharSequence charSequence){
        return charSequence == null || charSequence.length() == 0;
    }
    public static <E> boolean validCollectionIsEmpty(Collection<E> collection){
        return collection == null || collection.isEmpty();
    }
    public static <K,V> boolean validMapIsEmpty(Map<K,V> map){
        return map == null || map.isEmpty();
    }
}


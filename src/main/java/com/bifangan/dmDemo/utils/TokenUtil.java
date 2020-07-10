package com.bifangan.dmDemo.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by
 * 王尚飞
 * on 2020/7/9 15:13
 */
/**
 * @author Implements
 * @category 工具类
 */
@Component
@SuppressWarnings({ "unchecked", "rawtypes", "unused"})
public class TokenUtil {



    List list = new ArrayList();


    /**
     * Sha1加密
     *
     * @param
     *            str
     * @return String
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 字典排序
     *
     * @param list
     * @return List
     */
    public List dictSort(List list) {
        /*
         * 运用Collections的sort（）方法对其进行排序 sort（）方法需要传 连个参数，一个是需要进行排序的Collection
         * 另一个是一个Comparator
         */
        Collections.sort(list, new SpellComparator());
        return list;
    }

    /**
     * 归属于字典排序 汉字拼音排序比较器
     */
    class SpellComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            try {
                // 取得比较对象的汉字编码，并将其转换成字符串
                String s1 = new String(o1.toString().getBytes("UTF-8"), "ISO-8859-1");
                String s2 = new String(o2.toString().getBytes("UTF-8"), "ISO-8859-1");
                // 运用String类的 compareTo（）方法对两对象进行比较
                return s1.compareTo(s2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}

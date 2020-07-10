package com.bifangan.dmDemo.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class FaceIdUtil {

	private static AtomicInteger STUDENT_NUMBER = new AtomicInteger();
	
	public static String getFaceId(String name) {
		String result = null;
		result = String.format("%05d",STUDENT_NUMBER.getAndIncrement());
		result += "_";
		result += cnToUnicode(name);
		return result.replace("\\", "");
	}
	
    //Unicode转中文方法
    private static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
 
    //中文转Unicode
    private static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }
	
    public static void main(String[] args) {
    	System.out.println(getFaceId("李虹达"));
	}
}

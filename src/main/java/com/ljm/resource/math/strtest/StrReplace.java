package com.ljm.resource.math.strtest;

/**
 * 替换字符串中所有20%
 * @author liaojiamin
 * @Date:Created in 18:30 2020/11/3
 */
public class StrReplace {
    /**
     * 时间复杂度O(n) 空间复杂度O(n)
     * @return a
     * @author: liaojiamin
     * @Param str 目标字符串 '\0' 结束
     * @Param replaceChar 需替换字符
     * @param length 字符数组总长度
     * @date: 19:10 2020/11/3
     */
    public static char[] replaceFirst(char[] str, char replaceChar, int length){
        if(null == str || str.length == 0){
            return str;
        }
        int countStrNum = 0;
        int i = 0;
        while(str[i] != '\0'){
            i++;
            if(str[i] == replaceChar){
                countStrNum ++;
            }
        }
        if(countStrNum == 0){
            return str;
        }
        int resultStrLength = i + countStrNum*2;
        if(resultStrLength > length){
            return str;
        }
        char[] resultStr = new char[resultStrLength];
        int resultPosition = 0;
        for (int j = 0; j < i; j++) {
            char thisChar = str[j];
            if(thisChar == replaceChar){
                resultStr[resultPosition++]='%';
                resultStr[resultPosition++]='2';
                resultStr[resultPosition++]='0';
            }else {
                resultStr[resultPosition++] = thisChar;
            }
        }
        return resultStr;
    }

    /**
     * 时间复杂度O(n^2) 空间复杂度O(1)
     * @return a
     * @author: liaojiamin
     * @Param str 目标字符串 '\0' 结束
     * @Param replaceChar 需替换字符
     * @param length 字符数组总长度
     * @date: 19:10 2020/11/3
     */
    public static char[] replaceFirst1(char[] str, char replaceChar, int length){
        if(null == str || str.length == 0){
            return str;
        }
        int originStrLength = 0;
        int replaceStrCount = 0;
        while(str[originStrLength] != '\0'){
            originStrLength ++;
            if(str[originStrLength] == replaceChar){
                replaceStrCount ++;
            }
        }
        int resultLength = replaceStrCount * 2 + originStrLength;
        if(resultLength > length){
            return str;
        }
        int i = 0;
        while(str[i] != '\0'){
            if(str[i] == ' '){
                for (int j = originStrLength; j > i; j--) {
                    str[j+2] = str[j];
                }
                str[i++] = '%';
                str[i++] = '2';
                str[i++] = '0';
                originStrLength+=2;
            }else {
                i++;
            }
        }
        return str;
    }

    /**
     * 最优解：时间复杂度O(n) 空间复杂度O(1)
     * @return a
     * @author: liaojiamin
     * @Param str 目标字符串 '\0' 结束
     * @Param replaceChar 需替换字符
     * @param length 字符数组总长度
     * @date: 19:10 2020/11/3
     */
    public static char[] replaceFirst2(char[] str, char replaceChar, int length){
        if(null == str || str.length == 0){
            return str;
        }
        int originStrLength = 0;
        int replaceStrCount = 0;
        while(str[originStrLength] != '\0'){
            originStrLength ++;
            if(str[originStrLength] == replaceChar){
                replaceStrCount ++;
            }
        }
        int resultLength = replaceStrCount * 2 + originStrLength;
        if(resultLength > length){
            return str;
        }
        int i = resultLength;
        while (originStrLength >= 0){
            if(str[originStrLength] == ' '){
                str[i--] = '0';
                str[i--] = '2';
                str[i--] = '%';
            }else {
                str[i--] = str[originStrLength];
            }
            originStrLength --;
        }
        return str;
    }

    public static char[] getChar(String str, int length){
        char[] chars = new char[length];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }
        return chars;
    }
    public static void main(String[] args) {
        int length = 100;
        char[] chars = getChar("this is my way", length);
//        System.out.println(new String(replaceFirst(chars, ' ', length)));
//        System.out.println(new String(replaceFirst1(chars, ' ', length)));
        System.out.println(new String(replaceFirst2(chars, ' ', length)));
    }
}

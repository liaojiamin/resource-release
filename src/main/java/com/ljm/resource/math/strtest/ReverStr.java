package com.ljm.resource.math.strtest;

/**
 * 反转字符串中单词顺序，I am a software engineer -> engineer software a am I
 * @author liaojiamin
 * @Date:Created in 16:59 2021/7/1
 */
public class ReverStr {
    public static void main(String[] args) {
        String str = "I am a software engineer";
        char[] target = str.toCharArray();
        char[] result = reverMyStr(target);
        for (char c : result) {
            System.out.print(c);
        }
        char[] result_1 = reverTwice(target);
        for (char c : result_1) {
            System.out.print(c);
        }
        //首先应该对字符串判空,这里给定了,懒得判空了
        StringBuffer sb  = new StringBuffer();
        //把字符串用空格拆分
        String[] strs = str.split(" ");
        //从后向前遍历,并且拼接
        for (int i = strs.length-1; i >= 0; i--) {
            sb.append(strs[i]);
            if (i != 0){
                sb.append(" ");
            }
        }
        //输出结果:engineer software a am I
        System.out.println(sb.toString());

        //如果未给定指定位置,假设指定位置为n,此处第二个参数为n即可,建议判断一下n是否大于字符串长度
        //由于题目中给定了是4所以我这里就直接用了
        String headStr = str.substring(0,4);
        //此处n+1即可
        String tailStr = str.substring(5);
        String newStr = tailStr+headStr;
        //输出结果:a software engineerI am
        System.out.println(newStr);
    }



    /**
     * 方案二：反转两次
     * 先全部反转字符，在逐个单词颠倒
     * 时间复杂度O(n) 空间复杂度O(1)
     * */
    public static char[] reverTwice(char[] target){
        if(target == null || target.length <= 0){
            return target;
        }
        target = reverAll(target, 0, target.length -1);
        int[] isSpace = new int[target.length];
        int positionSpace = 0;
        for (int i = 0; i < target.length; i++) {
            if(target[i] == ' '){
                isSpace[positionSpace++] = i;
            }
        }
        positionSpace--;
        int start = 0;
        for (int i = 0; ; i++) {
            int end = target.length -1;
            if(i <= positionSpace){
                end = isSpace[i] - 1;
            }
            target = reverAll(target, start, end);
            if(i <= positionSpace){
                start = isSpace[i] +1;
            }else {
                return target;
            }
        }
    }

    /**
     * 按指定位置反转
     * */
    public static char[] reverAll(char[] target, int start, int end){
        while (start < end){
            char temp = target[start];
            target[start] = target[end];
            target[end] = temp;
            start++;
            end--;
        }
        return target;
    }

    /**
     * 方案一：空间换时间方法，
     * 空间复杂度O(n)，时间复杂度O(n)
     * */
    public static char[] reverMyStr(char[] target){
        if(target == null || target.length <= 0){
            return target;
        }
        int[] isSpace = new int[target.length];
        int positionSpace = 0;
        char[] result = new char[target.length];
        int resultPosition = 0;
        for (int i = 0; i < target.length; i++) {
            if(target[i] == ' '){
                isSpace[positionSpace++] = i;
            }
        }
        int positionEnd = target.length -1;
        positionSpace--;
        for (int i = positionSpace; ; i--) {
            int start = 0;
            if(i>=0){
                start = isSpace[i] + 1;
            }
            result = splitCharArray(start, positionEnd, target, result, resultPosition);
            resultPosition += (positionEnd - start +1);
            if(start != 0){
                //最后一次不需要再添加空格
                result[resultPosition++]=' ';
                positionEnd = isSpace[i] -1;
            }
            if(start == 0){
                return result;
            }
        }
    }

    public static char[] splitCharArray(int start, int end, char[] target, char[] result, int position){
        for(int i=start; i <=end;i++){
            result[position++] = target[i];
        }
        return result;
    }
}

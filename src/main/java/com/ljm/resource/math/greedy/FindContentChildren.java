package com.ljm.resource.math.greedy;

import java.util.Arrays;

/**
 * Created by jiamin5 on 2023/3/6.
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
 * 都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，
 * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/assign-cookies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindContentChildren {
    public static void main(String[] args) {
        FindContentChildren findContentChildren = new FindContentChildren();
        int[] g = {1,2,3,7,9,6,7,8,5,8,59,40,22};
        int[] s = {3,2,5,6,7};
        System.out.println(findContentChildren.findContentChildren(g, s));
    }

    //每个只能一块饼干
    public int findContentChildren(int[] g, int[] s) {
        g = quickSort(g);
        s = quickSort(s);
        int i=0;
        int j =0;
        while (i<g.length && j<s.length){
            if(g[i] <= s[j]){
                i++;
            }
            j++;
        }
        return i;
    }

    public int[] quickSort(int[] g) {
        if (g == null || g.length <= 0) {
            return g;
        }
        quickSort(g, 0, g.length - 1);
        for (int i : g) {
            System.out.print(i + ", ");
        }
        System.out.println();
        return g;
    }

    public int[] quickSort(int[] g, int start, int end) {
        if (start < end) {
            int middle = swap(g, start, end);
            quickSort(g, start, middle - 1);
            quickSort(g, middle + 1, end);
        }
        return g;
    }

    public int swap(int[] g, int start, int end) {
        if (start < end) {
            int position = g[start];
            while (start < end) {
                while (end > start && g[end] > position) {
                    end--;
                }
                if (end > start) {
                    g[start] = g[end];
                    start++;
                }
                while (end > start && g[start] < position) {
                    start++;
                }
                if (end > start) {
                    g[end] = g[start];
                    end--;
                }
            }
            g[start] = position;
        }
        return start;
    }


}

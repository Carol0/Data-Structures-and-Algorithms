package com.example.leetcode.prefixsum.algorithm;

/**
 * @author Carol
 * @date 2022/5/9
 * @since 1.0.0
 */
public class KmpMatch {
    public static void main(String[] args) {
        KmpMatch match = new KmpMatch();
        //0,0,1,1,2,3,4
        char[] modelStr = new char[]{'x', 'f'};
        char[] mainStr = new char[]{'a', 'b', 'a', 'x', 'a', 'a', 'f'};
        match.init(modelStr);
        System.out.println(match.kmp(mainStr, modelStr));
        System.out.println("test");
    }

    int[] next ;

    /**
     * 初始化next数组
     * @param modelStr
     */
    public void init(char[] modelStr) {
        //首先计算next数组
        //遍历modelStr，遍历到的字符与之前字符组成一个串
        //1。如果上一个字符的最长前缀子串的下一个字符==当前字符，当前字符最长子串直接家上当前字符即可
        //2。如果不等于，需要递归找到之前存在的最长前缀子串的下一个字符等于当前子串的，然后设置当前字符子串的最长前缀后缀子串
        //a b a a b a a
        next = new int[modelStr.length];
        //对于第一个字符
        int start = 0;
        while (start < modelStr.length) {
            next[start] = this.recursion(start, modelStr);
            ++ start;
        }
    }

    /**
     *
     * @param i 当前遍历到的字符
     * @return
     */
    private int recursion(int i, char[] modelStr) {
        //next记录的是个数，不是下标
        if (0 == i) {
            return 0;
        }
        int last = next[i -1];
        //没有匹配的,直接判断第一个是否匹配
        if (0 == last) {
            if (modelStr[last] == modelStr[i]) {
                return 1;
            }
            return 0;
        }
        //如果last不为0，有值，可以作为最长匹配的前缀
        if (modelStr[last] == modelStr[i]) {
            return next[i - 1] + 1;
        }
        //当next[i-1]对应的子串的下一个值与modelStr不匹配时，需要找到当前要找的最长匹配子串的次长子串
        //依据就是次长子串对应的子串的下一个字符==modelStr[i];
        int tempIndex = i;
        while (tempIndex > 0) {
            last = next[tempIndex - 1];
            //找到第一个下一个字符是当前字符的匹配子串
            if (modelStr[last] == modelStr[i]) {
                return last + 1;
            }
            -- tempIndex;
        }
        return 0;
    }
    public int kmp(char[] mainStr, char[] modelStr) {
        //开始进行匹配
        int i = 0, j = 0;
        //遍历匹配主串mainStr，每次匹配查看与modelStr前缀的匹配(aba)
        //a b a x f g h h f d g
        //a b a a b a a
        //1。如果匹配前缀为0，往后滑动一位，
        //2。如果具有匹配前缀，查看next数组，当前前缀的最长匹配前缀后缀为多少，滑动modelStr到匹配的后缀的起始位置
        //i表示主串的指针，只会往后移动，j表示modelStr的指针，前后都会移动
        while (i + modelStr.length <= mainStr.length) {
            while (j < modelStr.length) {
                //找到第一个不匹配的位置
                if (modelStr[j] != mainStr[i]) {
                    break;
                }
                ++ i;
                ++ j;
            }
            if (j == modelStr.length) {
                //证明完全匹配
                return i - j;
            }
            //开始利用next数组，查看可以滑动多少位
            //next[j]为匹配的长的,next[j]存储的是最长匹配个数
            //走到这里找到的是第一个不匹配的位置
            if (j == 0) {
                //next[0] = 0;
                ++ i;
                continue;
            }
            //好前缀需要-1；之前匹配过的不需要在继续匹配，模式串和主串指针均往后移，
            //a b a x f g h h f d g
            //a b a a b a a

            //从好前缀后一个匹配
            j = next[j - 1];
        }
        return -1;
    }


}
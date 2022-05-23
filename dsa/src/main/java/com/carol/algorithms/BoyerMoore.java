package com.example.leetcode.prefixsum.algorithm;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/5/7
 * @since 1.0.0
 */
public class BoyerMoore {

    public static void main(String[] args) {
        BoyerMoore moore = new BoyerMoore();
//        char[] mainStr = new char[]{'a','b', 'c', 'a', 'g', 'f', 'a', 'c', 'j', 'k', 'a', 'c', 'k', 'e', 'a', 'c'};
//        char[] modelStr = new char[]{'a', 'c', 'k', 'e', 'a', 'c'};
        char[] mainStr = new char[]{'a','c','a'};
        char[] modelStr = new char[]{'c','a'};
        System.out.println(moore.bmStrMatch(mainStr, modelStr));
    }

    int[] modelStrIndex;
    private void badCharInit(char[] modelStr) {
        modelStrIndex = new int[26];
        for (int i = 0 ; i < 26 ; i ++) {
            modelStrIndex[i] = -1;
        }
        for (int i = 0 ; i < modelStr.length ; i++) {
            modelStrIndex[modelStr[i] - 'a'] = i;
        }
    }

    //对应位置的前缀后缀是否匹配
    boolean[] isMatch;
    public void goodSuffixInit(char[] modelStr) {
        isMatch = new boolean[modelStr.length / 2];
        StringBuilder prefixStr = new StringBuilder();
        List<Character> suffixChar = new ArrayList<>(modelStr.length / 2);
        for (int i = 0 ; i < modelStr.length / 2 ; i ++) {
            prefixStr.append(modelStr[i]);
            suffixChar.add(0, modelStr[modelStr.length - i - 1]);
            isMatch[i] = this.madeSuffix(suffixChar).equals(prefixStr.toString());
        }
    }

    /**
     * @param mainStr 主串
     * @param modelStr 模式串
     * @param start 模式串在主串中的起始位置
     * @return 模式串可滑动距离，如果为0则匹配上
     */
    private int badChar(char[] mainStr, char[] modelStr, int start) {
        //1。从匹配串后往前找到主串中的坏字符，对应匹配串中位置si
        //2。查找当前坏字符在匹配串中位置，取靠后的
        //3。计算滑动距离
        //为了能快速找到坏字符的位置，我们利用一个数组存储匹配串中出现的位置，因为我们需要的是靠后的坏字符位置，所以后面直接覆盖前面没问题

        //坏字符位置
        int badCharIndex = -1;
        char badChar = '\0';
        //开始从匹配串后往前进行匹配
        for (int i = modelStr.length - 1 ; i >= 0 ; i --) {
            int mainStrIndex = start + i;
            if (mainStr[mainStrIndex] != modelStr[i]) {
                badCharIndex = i;
                badChar = mainStr[mainStrIndex];
                break;
            }
        }
        if (-1 == badCharIndex) {
            //不存在坏字符,需要移动距离为0
            return 0;
        }
        if (modelStrIndex[badChar - 'a'] > -1) {
            //出现过
            return badCharIndex - modelStrIndex[badChar - 'a'];
        }
        return modelStr.length;
    }

    /**
     *
     * @param mainStr 主串
     * @param modelStr 模式串
     * @param start 模式串在主串中起始位置
     * @return 模式串可滑动距离
     */
    private int goodSuffix(char[] mainStr, char[] modelStr, int start) {
        //1。找到好后缀，好后缀起始位置在匹配串中位置si
        //2。查看模式串中是否存在好后缀，存在好后缀（多个选取靠后的一个），起始位置xi，则匹配串需要前滑si-xi
        //如果当前好后缀不存在，为了避免过度滑动，则查看模式串前缀是否有与后缀匹配的串
        //为了能够快速比较，维护一个前缀字符串数组，一个后缀字符串数组

        //查找好后缀，不存在直接返回-1；
        int goodSuffixStartIndex = -1;
        StringBuilder goodSuffixStr = new StringBuilder();
        for (int i = modelStr.length - 1 ; i >= 0 ; i --) {
            int mainStrIndex = start + i;
            if (modelStr[i] != mainStr[mainStrIndex]) {
                break;
            }
            goodSuffixStartIndex = i;
            goodSuffixStr.append(modelStr[i]);
        }

        if (-1 == goodSuffixStartIndex) {
            //不存在好后缀，直接返回-1；
            return -1;
        }
        if (0 == goodSuffixStartIndex) {
            //完全匹配
            return 0;
        }
        //开始匹配好后缀，查找好后缀之前的子串是否有与好后缀一致的
        //暂时采用暴力匹配，先找到好后缀存在位置（等下可以修改为直接使用bm算法，也是一个匹配过程）
        int temp = modelStr.length - 1;
        //可能存在多个与好后缀匹配的，需要取最后一个,
        //反正匹配，找到第一个即可,匹配到第一个即停止
        int occurStartIndex = -1;
        for (int i  = goodSuffixStartIndex - 1 ; i >= 0 && temp >= goodSuffixStartIndex; -- i) {
            if (modelStr[i] == modelStr[temp]) {
                //匹配上一个，查看当前是否完成匹配，
                -- temp;
                if (temp == goodSuffixStartIndex) {
                    //已经成功匹配上，记录此时i，
                    occurStartIndex = i;
                    break;
                }
                continue;
            }
            //遇到不相等的，需要重新进行匹配
            temp = goodSuffixStartIndex;
        }
        //模式串中存在与好后缀匹配的子串
        if (-1 != occurStartIndex) {
            //则将这个匹配到的子串滑动到与好后缀匹配即可
            //此时需要滑动的距离
            return goodSuffixStartIndex - occurStartIndex;
        }

        //如果不存在与后缀匹配的字串，这时候为了不过度滑动
        //需要匹配串的前缀与好后缀的后缀进行匹配，滑动到匹配到的字串
        //eg:abab,如果后缀ab匹配上了，前面的ab没有匹配上，需要滑动两个单位，
        int index = -1;
        for (int i = 0 ; i < isMatch.length ; i ++) {
            if (isMatch[i]) {
                index = i;
            }
        }
        if (-1 == index) {
            //前缀后缀没有匹配上
            //直接滑动到最后面
            return modelStr.length;
        }
        return modelStr.length - index - 1;
    }

    /**
     * 组装后缀数据
     * @param characters
     * @return
     */
    private String madeSuffix(List<Character> characters) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : characters) {
            sb.append(ch);
        }
        return sb.toString();
    }


    public int bmStrMatch(char[] mainStr, char[] modelStr) {
        //初始化坏字符和好后缀需要的数据
        this.badCharInit(modelStr);
        this.goodSuffixInit(modelStr);
        int start = 0;
        while (start + modelStr.length <= mainStr.length) {
            int badDistance = this.badChar(mainStr, modelStr, start);
            int goodSuffixDistance = this.goodSuffix(mainStr, modelStr, start);
            System.out.println("badDistance = " +badDistance  + "， goodSuffixDistance = " + goodSuffixDistance);
            if (0 == badDistance || 0 == goodSuffixDistance) {
                System.out.println("匹配到的位置 ：" + start);
                return start;
            }
            start += Math.max(badDistance, goodSuffixDistance);
            System.out.println("滑动至：" + start);
        }
        return -1;
    }
}
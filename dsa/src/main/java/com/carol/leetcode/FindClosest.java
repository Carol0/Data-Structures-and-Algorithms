package com.example.leetcode.prefixsum.middle;

/**
 * @author Carol
 * @date 2022/5/27
 * @since 1.0.0
 */
public class FindClosest {
    /**
     * https://leetcode.cn/problems/find-closest-lcci/
     * 面试题 17.11. 单词距离
     * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
     * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
     * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
     * 输出：1
     */

    public static void main(String[] args) {
        FindClosest closest = new FindClosest();
        System.out.println(closest.findClosest(
                new String[]{"I","am","a","student","from","a","university","in","a","city"},
                "a", "student"));
    }
    public int findClosest(String[] words, String word1, String word2) {
        //使用两个指针分别指向word1和word2的位置，每次计算距离，存下最小距离
        int word1Index = -1;
        int word2Index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < words.length ; i++) {
            if (words[i].equals(word1)) {
                word1Index = i;
            }
            if (words[i].equals(word2)) {
                word2Index = i;
            }
            if (word1Index != -1 && word2Index != -1) {
                min = Math.min(min, Math.abs(word2Index - word1Index));
            }
        }
        return min;
    }
}
package com.carol.leetcode.day;

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

    /**
     * 思路：设置两个指针指向出现的word1，word2位置，初始值为-1，遍历words，每次遇到word1或者word2直接更新对应指针
     * 当两个指针的下标均不为-1时，直接计算下标距离，并与之前记录的最短下标距离相比较，取较小值，遍历完之后，即可找到word1和word2
     * 在words出现的最近距离
     * 查找word1和word2在words中相距最近的距离
     * @param words 查找的字符串数组
     * @param word1 word1
     * @param word2 word2
     * @return 相距最近的距离
     */
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
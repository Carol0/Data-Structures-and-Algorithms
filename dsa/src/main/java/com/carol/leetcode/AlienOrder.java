package com.carol.leetcode;

import java.util.*;

/**
 * @author Carol
 * @date 2022/5/31
 * @since 1.0.0
 */
public class AlienOrder {
    /**
     * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
     * 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
     * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。
     * 若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
     * 字符串 s 字典顺序小于 字符串 t 有两种情况：
     * 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么s 的字典顺序小于 t 。
     * 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
     * 输入：words = ["wrt","wrf","er","ett","rftt"]
     * 输出："wertf"
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 100
     * words[i] 仅由小写英文字母组成
     *
     *
     * 思路：根据所给的word判断相对顺序，所给的word每个字符之前有前驱后继关系
     * 前驱的相对位置在后继的前面，符合拓扑排序
     */
    public static void main(String[] args) {
        AlienOrder alienOrder = new AlienOrder();
        System.out.println( alienOrder.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
    }
    //不好区分是否出现过，入度为0时候不好存入
    // int[] inDegree = new int[26];
    //构造边关系，类邻接表
    Map<Integer, Integer> inDegree = new HashMap<>();
    Map<Integer, List<Integer>> edges = new HashMap<>();
    //标志字符是否合法
    boolean valid = true;
    public String alienOrder(String[] words) {
        /**
         * 记录当前字符的入度，入度为0，证明没有前驱节点，直接访问
         */
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (!inDegree.containsKey(ch - 'a')) {
                    inDegree.put(ch - 'a', 0);
                }
                if (!edges.containsKey(ch - 'a')) {
                    edges.put(ch - 'a', new ArrayList<>());
                }
            }
        }

        for (int i = 1 ; i < words.length ; i ++) {
            this.addEdge(words[i - 1], words[i]);
        }
        if (!valid) {
            return "";
        }

        //标志当前字符是否已经被访问
        int[] visited = new int[26];
        Deque<Integer> deque = new LinkedList<>();
        //找到入度为0的节点入队
        for (Integer key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                deque.push(key);
            }
        }
        //开始遍历
        StringBuilder result = new StringBuilder();
        while (!deque.isEmpty()) {
            Integer current = deque.poll();
            //标记为访问
            visited[current] = 1;
            result.append((char) (current + 'a'));
            //通过current找到所有连接节点，将入度-1，减完之后为0，则直接入队
            List<Integer> children = edges.get(current);
            for (Integer child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    deque.push(child);
                }
            }
        }
        return result.length() == edges.size() ? result.toString() : "";
    }

    /**
     * 每个单词中的字符具有前驱后继关系，单词之间也具有前驱后继关系
     * @param before
     * @param after
     */
    private void addEdge(String before, String after) {
        //"wrt","wrf","er"
        int length1 = before.length();
        int length2 = after.length();
        int length = Math.min(length1, length2);
        int index = 0 ;
        while (index < length) {
            if (before.charAt(index) != after.charAt(index)) {
                //存储前驱后继关系
                edges.get(before.charAt(index) - 'a').add(after.charAt(index) - 'a');
                //后继入度加1
                inDegree.put(after.charAt(index) - 'a', inDegree.get(after.charAt(index) - 'a') + 1);
                break;
            }
            ++ index;
        }
        if (index == length && length1 > length2) {
            valid = false;
        }
    }
}
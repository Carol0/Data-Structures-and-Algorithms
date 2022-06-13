package com.carol.leetcode;

import java.util.*;

/**
 * @author Carol
 * @date 2022/6/12
 * @since 1.0.0
 */
public class FindAndReplacePattern {
    /**
     * 890. 查找和替换模式
     * https://leetcode.cn/problems/find-and-replace-pattern/
     * 你有一个单词列表words和一个模式pattern，
     * 你想知道 words 中的哪些单词与模式匹配。
     *   不一定是替换为出现过的字符，没有出现过的也可以用来替换，但是一个字符只能与另一个字符对应
     * 如果存在字母的排列 p，使得将模式中的每个字母 x 替换为 p(x) 之后，
     * 我们就得到了所需的单词，那么单词与模式是匹配的。
     * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，
     * 没有两个字母映射到同一个字母。）
     * 返回 words 中与给定模式匹配的单词列表。
     * 你可以按任何顺序返回答案。
     * ["ef","fq","ao","at","lx"]
     * "ya"
     */
    public static void main(String[] args) {
        FindAndReplacePattern f = new FindAndReplacePattern();
        List<String> result = f.findAndReplacePattern(new String[]{"abc","deq","mee","aqq","dkd","ccc"},
                "abb");
        System.out.println("test");
    }
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        //思路：其实就是找abb bbc这种类似的同一类型的，pattern表示了一个abb或者bbc或者其他
        //当前word如果可以通过替换变成pattern则就是匹配，同一个字母只能替换成一个字母
        //1.当前word与pattern长度不一致，一定不能替换
        //2.遍历word的每一个字母，用一个map记录当前的映射关系，如果匹配或者符合映射关系
        //则记录，需要同时判断word匹配pattern和pattern匹配word。
        List<String> result = new ArrayList<>();
        //记录映射关系ab映射则记录两条ab和ba，
        for (String word : words) {
            if (match(word, pattern) && match(pattern, word)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean match(String word, String pattern) {
        if (word.length() != pattern.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0 ; i < pattern.length() ; i++) {
            char w = word.charAt(i), p = pattern.charAt(i);
            if (!map.containsKey(w)) {
                map.put(w, p);
                continue;
            }
            if (map.get(w) != p) {
                return false;
            }
        }
        return true;
    }
}
package com.carol.leetcode.dfs;

/**
 * @author Carol
 * @date 2022/9/19
 * @since 1.0.0
 */
public class LC211 {
    /**
     * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
     *
     * 实现词典类 WordDictionary ：
     *
     * WordDictionary() 初始化词典对象
     * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
     * bool search(word) 如果数据结构中存在字符串与word 匹配，则返回 true ；
     * 否则，返回 false 。word 中可能包含一些 '.' ，每个. 都可以表示任何一个字母。
     * 1 <= word.length <= 25
     * addWord 中的 word 由小写英文字母组成
     * search 中的 word 由 '.' 或小写英文字母组成
     * 最多调用 104 次 addWord 和 search
     * 思路：通过题目的描述，很容易对应到字典树结构，将单词构建成一棵多叉树，进行查找匹配
     * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
     * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
     *
     * ["WordDictionary","addWord","addWord","addWord","addWord",
     * [[],["at"],["and"],["an"],["add"],
     * "search","search",
     * ["a"],[".at"],
     * "addWord",
     * ["bat"]
     * "search","search","search","search","search","search"]
     * ,[".at"],["an."],["a.d."],["b."],["a.d"],["."]]
     */

    public static void main(String[] args) {
        LC211 lc211 = new LC211();
        lc211.addWord("at");
        lc211.addWord("and");
        lc211.addWord("an");
        lc211.addWord("add");
        System.out.println(lc211.search("a"));
        System.out.println(lc211.search(".at"));

        lc211.addWord("bat");

        System.out.println(lc211.search(".at"));
        System.out.println(lc211.search("an."));
        System.out.println(lc211.search("a.d."));
        System.out.println(lc211.search("b."));
        System.out.println(lc211.search("a.d"));
    }

    Trie head;
    public LC211() {
        head  = new Trie('\0', false);
    }
    public void addWord(String word) {
        head.insert(word);
    }

    public boolean search(String word) {
        return head.dfs(head, word.toCharArray(), 0);
    }
    /**
     * 字典树结构，这个节点应该能够找到他的下级节点（多叉子树），并且能够标志当前节点是否为单词结尾
     */
    public static class Trie {
        private char val;
        private boolean isEnd;
        private Trie[] children;

        public Trie(char val, boolean isEnd) {
            this.val =val;
            this.isEnd = isEnd;
            children = new Trie[26];
        }

        public void insert(String word) {
            //从跟节点开始遍历查找匹配word，构建字典树
            Trie temp = this;
            for (char w : word.toCharArray()) {
                boolean flag = true;
                for (Trie child : temp.children) {
                    if (null != child && child.val == w) {
                        //当前节点已经入字典树
                        flag = false;
                        temp = child;
                        break;
                    }
                }
                if (flag) {
                    //没有入字典树
                    temp.children[w - 'a'] = new Trie(w, isEnd);
                    temp = temp.children[w - 'a'];
                }
            }
            temp.isEnd = true;
        }

        //当通过通配符.去查找的时候，可能找错分支，从未错失正确答案
        //需要进行深度递归，对每一个符合条件的点进行递归查找
        public boolean dfs(Trie node, char[] chars, int index) {
            if (index == chars.length) {
                return node.isEnd;
            }
            boolean result  = false;
            for (Trie child : node.children) {
                if (null != child && (child.val == chars[index] || chars[index] == '.')) {
                    //只要有一个能匹配上都可以
                     result = (result || dfs(child, chars, ++ index));
                }
            }
            return result;
        }

        public boolean find(String word) {
            Trie trie = this;
            for (char ch : word.toCharArray()) {
                boolean flag = true;
                for (Trie child : trie.children) {
                    if ((null != child) && (child.val == ch || ch == '.')) {
                        trie = child;
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return false;
                }
            }
            return trie.isEnd;
        }
    }
}
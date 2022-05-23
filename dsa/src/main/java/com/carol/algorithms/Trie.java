package com.example.leetcode.prefixsum.algorithm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/5/11
 * @since 1.0.0
 */
public class Trie {

    public static void main(String[] args) {
        //he, him, lot, a
        //初始化Tire树
        Trie trie = new Trie();
        trie.init();
        //构建Tire树，只有以下单词才是有效单词
        trie.insertStr("he".toCharArray());
        trie.insertStr("him".toCharArray());
        trie.insertStr("lot".toCharArray());
        trie.insertStr("a".toCharArray());
        //匹配字符串是否为有效单词
        List<String> strings = trie.findStrPrefix("h");
        System.out.println("");

    }


    Node root;


    /**
     * 将当前串插入字典树
     * @param chars
     */
    public void insertStr(char[] chars) {
        //首先判断首字符是否已经在字典树中，然后判断第二字符，依次往下进行判断，找到第一个不存在的字符进行插入孩节点
        Node p = root;
        //表明当前处理到了第几个字符
        int chIndex = 0;
        while (chIndex < chars.length) {
            while (chIndex < chars.length && null != p) {
                Node[] children = p.childNode;
                boolean find = false;
                for (Node child : children) {
                    if (null == child) {continue;}
                    if (child.value == chars[chIndex]) {
                        //当前字符已经存在，不需要再进行存储
                        //从当前节点出发，存储下一个字符
                        p = child;
                        ++ chIndex;
                        find = true;
                        break;
                    }
                }
                if (Boolean.TRUE.equals(find)) {
                    //在孩子中找到了 不用再次存储
                    break;
                }
                //如果把孩子节点都找遍了，还没有找到这个字符，直接将这个字符加入当前节点的孩子节点
                Node node = new Node(chars[chIndex]);
                node.childNode = new Node[26];
                children[chars[chIndex] - 'a'] = node;
                p = node;
                ++ chIndex;
            }
        }
        //字符串中字符全部进入tire树中后，将最后一个字符所在节点标志为结尾节点
        p.isTail = true;
    }

    /**
     * 找到所有以str为前缀的字符串
     * @param str 前缀串
     * @return 所有以str为前缀的单词
     */
    public List<String> findStrPrefix(String str) {
        //根据str首先找到str最后一个字符，然后从这个字符出发，找到所有字符串
        List<String> result = new ArrayList<>();
        char[] chars = str.toCharArray();
        //分成两步走
        //1。找到str最后一个自字符在字典树中的node
        //2。从该node出发，找到所有的结尾node，即为以str为前缀的字符串
        int chIndex = 0;
        Node p = root;
        while (null != p && chIndex < chars.length) {
            Node[] children = p.childNode;
            boolean flag = false;
            for (Node child : children) {
                if (null == child) {continue;}
                if (child.value == chars[chIndex]) {
                    //已经找到
                    p = child;
                    flag = true;
                    ++ chIndex;
                    break;
                }
            }
            //如果没有找到，直接返回空
            if (Boolean.FALSE.equals(flag)) {
                return null;
            }
        }
        //找到了最后一个节点
        //深度优先遍历，查找所有尾节点
        this.dfs(p, new StringBuilder(str), result);
        return result;
    }

    public void dfs(Node p, StringBuilder str, List<String> result) {
        Node[] children = p.childNode;
        for (Node child : children) {
            if (null == child) {
                continue;
            }
            str.append(child.value);
            if (child.isTail) {
                result.add(str.toString());
            }
            //再递归查当前节点的孩子节点
            dfs(child, str, result);
            //需要将刚刚set进去的节点删除，否则影响当前节点的下一个孩子节点
            //举个例子，h的孩子节点有e，i，当e放进去之后不拿出来，在遍历到i的时候，就会形成hei
            str.setLength(str.length() - 1);
        }
    }
    /**
     * 查看当前字符串是否可以在trie中找到
     * @param str 主串
     * @return true/false
     */
    public boolean isMatch(String str) {
        //从root开始进行匹配，只要有一个找不到即为匹配失败
        char[] chars = str.toCharArray();
        int chIndex = 0;
        Node p = root;
        while (null != p) {
            Node[] children = p.childNode;
            boolean flag = false;
            for (Node child : children) {
                if (null == child) {continue;}
                if (child.value == chars[chIndex]) {
                    flag = true;
                    p = child;
                    ++ chIndex;
                    //当比较最后一个字符的时候，这个字符需要是结尾字符才能完全匹配
                    if (chIndex == chars.length && p.isTail) {
                        return true;
                    }
                    break;
                }
            }
            if (Boolean.FALSE.equals(flag)) {
                return false;
            }
        }
        return false;
    }

    public void init() {
        root = new Node('\0');
        root.childNode = new Node[26];
    }

    /**
     * Trie树节点
     * 假设我们只做26个小写字母下的匹配
     */
    public static class Node{
        //当前节点值
        private char value;
        //当前节点的孩子节点
        private Node[] childNode;
        //标志当前节点是否是某单词结尾
        private boolean isTail;
        public Node(char value) {
            this.value = value;
        }
    }

}
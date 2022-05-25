package com.carol.algorithms;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Carol
 * @date 2022/5/12
 * @since 1.0.0
 */
public class ACAutomaton {

    /**
     * ac自动机是一个多模式匹配算法，结合了Tire树和KMP算法
     * Tire通过压缩公共前缀，使的利于前缀匹配，KMP通过一个next数组，跳过next[i]个字符达到减少不必要匹配的目的
     * 而在AC自动机中，我们首先建立一颗Tire树，然后对Tire上的节点，构建next节点，即如果达到这个节点之后下一个节点不匹配我们可以跳到下一个节点进行匹配
     * 上一节实现的Tire树此时还差个转移指针
     */


    public static void main(String[] args) {
        ACAutomaton acAutomaton = new ACAutomaton();
        //初始化一个仅有根节点的字典树
        acAutomaton.init();
        //构建Tire树
        acAutomaton.insertStr("out".toCharArray());
        acAutomaton.insertStr("about".toCharArray());
        acAutomaton.insertStr("act".toCharArray());
        System.out.println("ceshi");
        //构建失败指针
        acAutomaton.madeFailNext();
        System.out.println("ces");
        //匹配
        List<String> result = acAutomaton.match("abcdeasactdaboutcebcd");
        System.out.println("res");

    }

    Node root;

    public void init() {
        root = new Node('\0');
        root.childNode = new Node[26];
    }
    //插入字符串进入Tire树，与Tire一致
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
        p.tailLength = chars.length;
    }

    /**
     * 构建失败指针
     * 从根节点开始层序遍历树结构，构建失败指针
     * 一个节点的子节点的失败指针可以根据当前节点的失败指针得到，从跟节点到当前节点的某后缀与根节点到失败指针匹配，
     * 那么当前节点的子节点的失败指针则可以根据当前节点的失败指针，然后匹配失败指针的下一个节点是否==子节点，==的话直接指向这个节点
     * 如果找完之后还没有找到，则指向根节点，证明下一次必须从根节点开始匹配。
     */
    public void madeFailNext() {
        //层序遍历，为了保证求解这个节点失败指针的时候，它的父节点的失败指针以及失败指针的失败指针。。。。已经求得，可以完全根据这个找
        Deque<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            Node[] children = current.childNode;
            for (Node child : children) {
                if (null == child) {
                    continue;
                }
                Node failNode = current.failNode;
                while (null != failNode) {
                    //找到当前节点的失败指针，查看失败指针子节点是否有==
                    Node[] failChildren = failNode.childNode;
                    Node node = failChildren[child.value - 'a'];
                    if (null == node) {
                        //找当前指针的下一个指针
                        failNode = failNode.failNode;
                        continue;
                    }
                    //已经找到匹配的
                    //将失败指针指向node
                    child.failNode = node;
                    break;
                }
                //如果找完还没有找到，指向root
                if (null == failNode) {
                    child.failNode = root;
                }
                nodes.add(child);
            }
        }
    }

    /**
     * 匹配出str中所有出现的关键词
     * @param str
     * @return
     */
    public List<String> match(String str) {
        //遍历当前子串串，从根节点出发，如果匹配就一直往下进行匹配，同时需要看匹配的节点是否为结尾节点，如果是，匹配上一个
        //如果不匹配则通过失败指针转移到下一个节点
        this.dfs(root, 0, str);
        return machStr;
    }

    //abcdeasdabcebcd
    List<String> machStr = new ArrayList<>();
    private void dfs(Node node, int chIndex, String chars) {
        if (chIndex >= chars.length()) {
            return;
        }
        //从将当前字符与当前node的孩子节点进行匹配，如果当前字符与node的孩子节点.value匹配，判断当前字符是否为尾节点，是，则记录，匹配到了一个
        //继续匹配（子节点，与下一个元素进行匹配）
        //如果不匹配，则转到失败指针
        Node[] children = node.childNode;
        Node child = children[chars.charAt(chIndex) - 'a'];
        if (null == child) {
            //不匹配，转到失败指针
            //如果当前node==root，从root匹配，root的失败指针是null
            if (node == root) {
                dfs(root, ++ chIndex, chars);
            } else {
                dfs(node.failNode, chIndex, chars);
            }
        } else {
            //匹配到了
            if (child.isTail) {
                //并且是结尾节点，取从child.value到child.tailLength的字符
                machStr.add(chars.substring(chIndex - child.tailLength  + 1, chIndex + 1));
            }
            dfs(child, ++ chIndex, chars);
        }

    }



    public static class Node{
        //当前节点值
        private char value;
        //当前节点的孩子节点
        private Node[] childNode;
        //标志当前节点是否是某单词结尾
        private boolean isTail;
        //失败指针
        private Node failNode;
        //匹配串长度，当isTail==true时，表示从root当当前位置是一个完整的匹配串，记录这个匹配串的长度，便于之后快速找到匹配串
        private Integer tailLength;
        public Node(char value) {
            this.value = value;
        }
    }
}
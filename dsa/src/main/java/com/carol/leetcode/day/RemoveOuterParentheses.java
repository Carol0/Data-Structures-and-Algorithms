package com.carol.leetcode.day;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Carol
 * @date 2022/5/28
 * @since 1.0.0
 */
public class RemoveOuterParentheses {
    /**
     *
     * 2022-05-28 每日一题 1021. 删除最外层的括号
     * https://leetcode.cn/problems/remove-outermost-parentheses/
     * 有效括号字符串为空 ""、"(" + A + ")"或A + B ，其中A 和B都是有效的括号字符串，+代表字符串的连接。
     * 例如，""，"()"，"(())()"和"(()(()))"都是有效的括号字符串。
     * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B的方法，我们称其为原语（primitive），其中A 和B都是非空有效括号字符串。
     * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中P_i是有效括号字符串原语。
     * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
     *
     * 提示：
     * 1 <= s.length <= 105
     * s[i] 为 '(' 或 ')'
     * s 是一个有效括号字符串
     */

    public static void main(String[] args) {
        RemoveOuterParentheses removeOuterParentheses = new RemoveOuterParentheses();
        System.out.println(removeOuterParentheses.removeOuterParentheses("(()())"));
        System.out.println(removeOuterParentheses.removeOuterParentheses2("(()())"));
    }
    /**
     * 思路：遍历s，利用辅助栈，遇左括号入栈，遇右括号将栈顶元素出栈，如果出栈后，栈为空，则这时候得到一个有效括号
     * 按照题意剥离外层括号，继续往后遍历
     * @param s
     * @return
     */
    public String removeOuterParentheses(String s) {
        Deque<Character> deque = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (deque.isEmpty()) {
                //第一次或者出栈完成
                if (temp.length() > 1) {
                    result.append(temp.subSequence(1, temp.length() - 1));
                }
                temp = new StringBuilder();
            }
            if (ch == '(') {
                deque.push(ch);
                temp.append(ch);
                continue;
            }
            deque.pop();
            temp.append(ch);
        }
        if (temp.length() > 1) {
            result.append(temp.subSequence(1, temp.length() - 1));
        }
        return result.toString();
    }

    /**
     * 计数方式：遍历s，利用一个num记录出现的未进行配对的左括号的数量，即当前遍历字符为（时，num+1，当前为）时
     * 可以直接配对，num-1，当num为0时，证明完成一个有效括号，可以开始进行外层括号剥离，并计入result中，
     * 相比较上一个方法节省了空间
     * @param s
     * @return
     */
    public String removeOuterParentheses2(String s) {
        int num = 0;
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (num == 0 && temp.length() > 1) {
                result.append(temp.subSequence(1, temp.length() - 1));
                temp = new StringBuilder();
            }
            temp.append(ch);
            if (ch == ')') {
                -- num;
                continue;
            }
            ++ num;
        }
        if (num == 0 && temp.length() > 1) {
            result.append(temp.subSequence(1, temp.length() - 1));
        }
        return result.toString();
    }
}
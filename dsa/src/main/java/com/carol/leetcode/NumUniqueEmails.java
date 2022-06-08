package com.carol.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Carol
 * @date 2022/6/4
 * @since 1.0.0
 */
public class NumUniqueEmails {
    /**
     * 每个 有效电子邮件地址 都由一个 本地名 和一个 域名 组成，以 '@' 符号分隔。除小写字母之外，
     * 电子邮件地址还可以含有一个或多个'.' 或 '+' 。
     * 例如，在alice@leetcode.com中，alice是 本地名 ，而leetcode.com是 域名 。
     * 如果在电子邮件地址的 本地名 部分中的某些字符之间添加句点（'.'），
     * 则发往那里的邮件将会转发到本地名中没有点的同一地址。请注意，此规则 不适用于域名 。
     * 例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com”会转发到同一电子邮件地址。
     * 如果在 本地名 中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件。
     * 同样，此规则 不适用于域名 。
     * 例如 m.y+name@email.com 将转发到 my@email.com。
     * 可以同时使用这两个规则。
     * 给你一个字符串数组 emails，我们会向每个 emails[i] 发送一封电子邮件。
     * 返回实际收到邮件的不同地址数目。
     *
     * 思路：将电子邮件分为@前和后，前面的本地地址匹配具有一定的规则，不一定需要全匹配，后面的域名则需要全匹配
     * 可以利用set存储处理后的电子邮件，处理完之后直接返回set大小
     *
     */

    public static void main(String[] args) {
        NumUniqueEmails numUniqueEmails = new NumUniqueEmails();
        int result = numUniqueEmails.numUniqueEmails(new String[]{"a@leetcode.com","b@leetcode.com","c@leetcode.com"});
        System.out.println(result);
    }

    public int numUniqueEmails(String[] emails) {
        //思路：电子邮件
        Set<String> result = new HashSet<>();
        for (String email : emails) {
            //分割电子邮件为本地地址和域名
            String[] strings = email.split("@");
            //以+分割本地地址，保留第一个
            String[] local = strings[0].split("\\+");
            //将所有.去掉再与@和后面的域名拼成一个合法电子邮件
            //这里之前没有拼装@，导致样例没有完全通过，原因是存在前缀和后缀虽然不同，但是拼起来
            //恰好一致的情况，还是的全拼接完成
            result.add(local[0].replace(".", "") + "@" +  strings[1]);
        }
        return result.size();
    }
}
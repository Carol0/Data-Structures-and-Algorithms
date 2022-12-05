package com.carol.interview.bytedance;

/**
 * @author Carol
 * @date 2022/12/5
 * @since 1.0.0
 */
public class RepairSpell {
    /**
     * 1.
     * 万万没想到之聪明的编辑
     * 我叫王大锤，是一家出版社的编辑。我负责校对投稿来的英文稿件，这份工作非常烦人，因为每天都要去修正无数的拼写错误。但是，优秀的人总能在平凡的工作中发现真理。我发现一个发现拼写错误的捷径：
     *
     * 1. 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
     * 2. 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
     * 3. 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC
     * 数据范围：  ，每个用例的字符串长度满足
     * 输入例子：
     * 2
     * helloo
     * wooooooow
     * 输出例子：
     * hello
     * woow
     */
    public static void main(String[] args) {
        RepairSpell repairSpell = new RepairSpell();
        System.out.println(repairSpell.repairSpell("wooooooow"));
    }

    /**
     * 滑动窗口
     * 设置一个变量answer，存储最终结果
     * 窗口内的字符串设置为temp
     * 1。当temp长度为1，如果当前的字符==temp中的字符，将当前字符放入temp中进行下一次的判断，但是如果当前当前字符!=temp中的字符，将temp中的
     * 字符放入answer中
     * 2。当temp长度为2，如果当前字符==temp[0]，那直接舍弃当前字符，进行下一次判断，如果当前字符！=temp[0]，可能符合AABB型，需要进行下一次判断
     * 3。当temp长度>=3，如果当前字符是否==temp[temp.length - 1]，AABB型，直接舍弃当前字符，如果不等于，temp中的字符直接为正确答案，放入answer中
     * @param words
     * @return
     */
    public String repairSpell(String words) {
        StringBuilder answer = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        temp.append(words.charAt(0));
        for (int i = 1 ; i < words.length() ; i++) {
            if (temp.length() == 1) {
                if (words.charAt(i) == temp.charAt(0)) {
                    temp.append(words.charAt(i));
                } else {
                    answer.append(temp);
                    temp.setLength(0);
                    temp.append(words.charAt(i));
                }
                continue;
            }
            if (temp.length() == 2) {
                if (words.charAt(i) == temp.charAt(0)) {
                    continue;
                }
                temp.append(words.charAt(i));
            }
            if (temp.length() >= 3) {
                if (words.charAt(i) == temp.charAt(temp.length() - 1)) {
                    continue;
                }
                answer.append(temp);
                temp.setLength(0);
                temp.append(words.charAt(i));
            }
        }
        answer.append(temp);
        return answer.toString();
    }
}
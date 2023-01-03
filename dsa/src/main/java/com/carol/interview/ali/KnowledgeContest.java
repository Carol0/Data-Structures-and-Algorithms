package com.carol.interview.ali;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Carol
 * @date 2023/1/3
 * @since 1.0.0
 */
public class KnowledgeContest {
    /**
     * 最近部门要选两个员工去参加一个需要合作的知识竞赛，
     * 每个员工均有一个推理能力值 Ai ，以及一个阅读能力值 Bi。
     * 如果选择第 i个人和第 j 个人去参加竞赛，
     * 那么他们在阅读方面所表现出的能力为 X=(Bi+Bj)/2，
     * 他们在推理方面所表现出的能力为 Y=(Ai+Aj)/2,
     * 现在需要最大化他们表现较差一方面的能力，即让 min(x,y)尽可能大，问这个值最大是多少。
     * 第一行一个正整数n ，代表员工数。
     * 接下来n 行每行两个正整数 Ai，Bi
     * 分别用来描述第 i 个员工的推理和阅读能力。
     *
     * 输入例子：
     * 3
     * 2 2
     * 3 1
     * 1 3
     * 输出例子：
     * 2.0
     * 例子说明：
     * 选择第一个和第二个员工或第一个和第三个时，较差方面的能力都是1.5，选择第二个和第三个时较差方面能力是2.0。
     * 思路：因为最终选择的答案是两个能力中的偏小值，所以为了能选出较大的值，需要一个人的两个值偏差不能过大
     * 根据一个人的两个能力的差值的绝对值从小到大进行排序
     * (a1,b1),(a2,b2)...(ai,bi)...(aj,bj)..(an,bn)
     * (aj,bj) 假设aj < bj,那么我需要一个人尽可能的提升弱势，所以在已知的人员中找到max(a) = ai,
     * Y = (aj + ai) / 2;
     * abs(ai-bi) < abs(aj-bj)
     * |ai - bi| < bj - aj
     * 如果ai > bi 那么 ai - bi < bj - aj -> ai + aj < bi + bj -> min(x,y) = (ai+aj)/2
     * 如果bi > ai 并且已经假设aj<bj ai+bi < bi+bj -> min(x,y) = (ai + aj)/2
     * 所以：对于当前员工，需要找到另一个员工与他结合是的min（x,y）是最大的话，应该找当前员工的最小能力的最大值的员工
     * 每次更新answer，遍历完成之后就可以得到最终最优解
     */

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Node[] nodes = new Node[n];
        for (int i = 0 ; i < n ; i ++) {
            String[] nums = reader.readLine().split(" ");
            nodes[i] = new Node(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]));
        }
        KnowledgeContest k = new KnowledgeContest();
        System.out.println(k.knowledgeContest(nodes));
    }
    private double knowledgeContest(Node[] nodes){
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.abs));
        int maxA = nodes[0].a;
        int maxB = nodes[0].b;
        double answer = 0;
        for (int i = 1 ; i < nodes.length ; i++) {
            if (nodes[i].a < nodes[i].b) {
                answer = Math.max(answer, ((double) nodes[i].a + maxA) / 2);
            } else {
                answer = Math.max(answer, ((double) nodes[i].b + maxB) / 2 );
            }
            maxA = Math.max(maxA, nodes[i].a);
            maxB = Math.max(maxB, nodes[i].b);
        }
        return answer;

    }

    public static class Node {
        private int a;
        private int b;
        private int abs;
        Node(int a, int b) {
            this.a = a;
            this.b = b;
            this.abs = Math.abs(a - b);
        }
    }
}
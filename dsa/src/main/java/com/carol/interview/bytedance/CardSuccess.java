package com.carol.interview.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Carol
 * @date 2022/12/7
 * @since 1.0.0
 */
public class CardSuccess {
    /**
     * 3.具体的规则如下：
     * 总共有36张牌，每张牌是1~9。每个数字4张牌。
     * 你手里有其中的14张牌，如果这14张牌满足如下条件，即算作和牌
     * 14张牌中有2张相同数字的牌，称为雀头。
     * 除去上述2张牌，剩下12张牌可以组成4个顺子或刻子。顺子的意思是递增的连续3个数字牌（例如234,567等），刻子的意思是相同数字的3个数字牌（例如111,777）
     *
     * 例如：
     * 1 1 1 2 2 2 6 6 6 7 7 7 9 9 可以组成1,2,6,7的4个刻子和9的雀头，可以和牌
     * 1 1 1 1 2 2 3 3 5 6 7 7 8 9 用1做雀头，组123,123,567,789的四个顺子，可以和牌
     * 1 1 1 2 2 2 3 3 3 5 6 7 7 9 无论用1 2 3 7哪个做雀头，都无法组成和牌的条件。
     *
     * 现在，小包从36张牌中抽取了13张牌，他想知道在剩下的23张牌中，再取一张牌，取到哪几种数字牌可以和牌。
     * 输入例子：
     * 1 1 1 2 2 2 5 5 5 6 6 6 9
     * 输出例子：
     * 9
     * 例子说明：
     * 可以组成1,2,6,7的4个刻子和9的雀头
     * 输入例子：
     * 1 1 1 1 2 2 3 3 5 6 7 8 9
     * 输出例子：
     * 4 7
     * 例子说明：
     * 用1做雀头，组123,123,567或456,789的四个顺子
     * 思路：
     * 题目要求：给定的13张牌，在生效的牌中选取一张使其符合胡牌规则，需要求这张牌的可能取值
     * 1。把36张牌分为两个部分 used， unUsed
     * 2。从unUsed的数组中选取一张牌，放入used中，然后校验我们的used是否符合胡牌规范
     * 3。问题转化为used中的牌是否符合胡牌规范
     *  a.先取出雀头（两个相同的数字）
     *  b.判断剩下的是否可以全部组成三个一样的或者连子，优先取三个一样的，然后再取顺子
     */

    public static void main(String[] args) {
        CardSuccess cardSuccess = new CardSuccess();
        List<Integer> result = cardSuccess.cardSuccess(new int[]{1,1,1,1,2,2,3,3,5,6,7,8,9});
        System.out.println("sdds");
    }

    public List<Integer> cardSuccess(int[] cards) {
        //初始化used和unUsed数组
        //下标index表示数字，数组值表示出现了多少次
        int[] used = new int[10];
        int[] unUsed = new int[10];
        for (int card : cards) {
            ++ used[card];
        }
        for (int i = 1; i < unUsed.length ; i ++) {
            unUsed[i] = 4 - used[i];
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 1 ; i < unUsed.length ; i ++) {
            if (unUsed[i] < 1) {
                continue;
            }
            ++ used[i];
            if (check(used)) {
                answer.add(i);
            }
            -- used[i];
        }
        return answer;
    }

    private boolean check(int[] used) {
        //1.取出雀头
        for (int i = 1; i < used.length ; i++) {
            int[] tempUsed = Arrays.copyOf(used, used.length);
            if (tempUsed[i] >= 2) {
                //可以作为雀头
                tempUsed[i] -= 2;
                //开始校验顺子和三个一样的元素
                int j = 1;
                while (j < tempUsed.length) {
                    if (tempUsed[j] == 0) {
                        ++ j;
                        continue;
                    }
                    //优先处理为刻子
                    if (tempUsed[j] >= 3) {
                        tempUsed[j] -= 3;
                        continue;
                    }
                    //处理顺子
                    if (j + 2 < tempUsed.length && tempUsed[j + 1] > 0 && tempUsed[j + 2] > 0) {
                        -- tempUsed[j];
                        -- tempUsed[j + 1];
                        -- tempUsed[j + 2];
                        continue;
                    }
                    break;

                }
            }
            //查看是否和牌
            int sum = 0;
            for (int k = 1 ; k < tempUsed.length ; k ++) {
                sum += tempUsed[k];
            }
            if (sum == 0) {
                return true;
            }
        }
        return false;
    }

}
package com.carol.interview.bytedance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carol
 * @date 2022/12/8
 * @since 1.0.0
 */
public class FeatureExtraction {
    /**
     * 4.
     * 特征提取
     *    一个猫咪特征是一个两维的vector<x, y>。如果x_1=x_2and y_1=y_2，那么这俩是同一个特征。
     *    因此，如果喵咪特征连续一致，可以认为喵咪在运动。也就是说，如果特征<a, b>在持续帧里出现，那么它将构成特征运动。
     *    比如，特征<a, b>在第2/3/4/7/8帧出现，那么该特征将形成两个特征运动2-3-4 和7-8。
     * 现在，给定每一帧的特征，特征的数量可能不一样。小明期望能找到最长的特征运动。
     * 输入描述：
     * 接下来的M行，每行代表一帧。其中，第一个数字是该帧的特征个数，接下来的数字是在特征的取值；比如样例输入第三行里，2代表该帧有两个猫咪特征，<1，1>和<2，2>
     * 所有用例的输入特征总数和<100000
     * M满足1≤M≤10000，一帧的特征个数满足 ≤ 10000。
     * 特征取值均为非负整数。
     * 输入例子：
     * 2 1 1 2 2
     * 2 1 1 1 4
     * 2 1 1 2 2
     * 2 2 2 1 4
     * 0
     * 0
     * 1 1 1
     * 1 1 1
     * 输出：3
     * 思路：需要找到哪一个特征连续出现最多次
     * 我们在判断当前帧的特征是否在前一帧中出现，那么我们需要记录前一帧中的特征
     * 使用一个lastMap记录上一帧中特征连续出现的次数
     * 再使用一个resultMap记录到当前为止，所有出现的最大的特征的连续数量
     */

    public static void main(String[] args) {
        FeatureExtraction featureExtraction = new FeatureExtraction();
        int[][] frames = new int[][]{{2,1, 1, 2, 2},{2, 1, 1, 1, 4},{2 ,1 ,1, 2, 2},{2, 2, 2, 1, 4},{0},{0},{1,1,1},{1,1,1}};
        System.out.println(featureExtraction.featureExtraction(frames));
    }

    public int featureExtraction(int[][] frames) {
        //有一种特殊情况，就是frames有很多行，但是每一行都是0个特征，应该返回0，这里暂时不考虑
        if (frames.length < 2) {
            return frames.length;
        }
        Map<String, Integer> lastFeature = new HashMap<>();
        Map<String, Integer> answerFeature = new HashMap<>();
        for (int i = 0 ; i < frames.length ; i ++) {
            Map<String, Integer> tempLastFeature = new HashMap<>();
            for (int j = 1; j < frames[i].length ; j += 2) {
                String feature = frames[i][j] + "," + frames[i][j + 1];
                if (lastFeature.containsKey(feature)) {
                    tempLastFeature.put(feature, lastFeature.get(feature) + 1);
                } else {
                    tempLastFeature.put(feature, 1);
                }
            }
            for (String key : lastFeature.keySet()) {
                if (answerFeature.containsKey(key)) {
                    answerFeature.put(key, Math.max(answerFeature.get(key), lastFeature.get(key)));
                } else {
                    answerFeature.put(key, lastFeature.get(key));
                }
            }
            lastFeature = tempLastFeature;
        }
        int answer = 0;
        for (String key : answerFeature.keySet()) {
            answer = Math.max(answer, answerFeature.get(key));
        }
        return answer;
    }
}
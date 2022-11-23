package com.carol.interview.bytedance;

import java.util.*;

/**
 * @author Carol
 * @date 2022/11/23
 * @since 1.0.0
 */
public class LoopCall {
    /**
     * 【字节】在分布式项目中，各个微服务之间可以相互调用，现在给你一个字符串二维数组call，
     * call[i]=["x","y"]，表示微服务"x"调用微服务"y"，请你判断这个任务数组中是否存在循环调用，如果存在，true，不存在返回false
     * 输入：[["A","B"],["B","C"]]
     * 输出：false
     * 输入：[["A","B"],["B","C"],["C","A"],["C","D"]]
     * 输出：true
     * 思路：实际上考察的是图的拓扑排序
     * 1.表示出有向图
     * 2.有向图进行拓扑排序
     */
    public static void main(String[] args) {
        String[][] call = new String[][]{{"A","B"},{"B","C"},{"C","A"},{"C","D"}};
        LoopCall loopCall = new LoopCall();
        System.out.println(loopCall.isLoopCall(call));
    }

    public boolean isLoopCall(String[][] call) {
        //1.表示出图,key:表示的节点C，value表示后继【A，D】
        Map<String, List<String>> graph = this.initGraph(call);
        //2.开始进行拓扑排序
        //a.申请一个map用来记录节点的入度
        Map<String, Integer> inDegree = this.buildInDegree(graph);
        //3。进行拓扑排序
        return this.sort(graph, inDegree);

    }

    private boolean sort(Map<String, List<String>> graph, Map<String, Integer> inDegree) {
        Set<String> visited = new HashSet<>();
        Deque<String> deque = new LinkedList<>();
        //寻找入度为0的节点
        for (String key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                deque.add(key);
            }
        }
        while (!deque.isEmpty()) {
            String currentNode = deque.poll();
            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);
            List<String> nodes = graph.get(currentNode);
            for (String node : nodes) {
                inDegree.put(node, inDegree.get(node) - 1);
                if (inDegree.get(node) == 0) {
                    deque.add(node);
                }
            }
        }
        return !(visited.size() == graph.size());
    }

    /**
     * 初始化一个图
     * @param call
     * @return
     */
    private Map<String, List<String>> initGraph(String[][] call) {
        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0 ; i < call.length ; i++) {
            if (!graph.containsKey(call[i][0])) {
                graph.put(call[i][0], new ArrayList<>());
            }
            if (!graph.containsKey(call[i][1])) {
                graph.put(call[i][1], new ArrayList<>());
            }
            List<String> nodes = graph.get(call[i][0]);
            nodes.add(call[i][1]);
            graph.put(call[i][0], nodes);
        }
        return graph;
    }

    /**
     * 获取图节点的入度
     * @param graph
     * @return
     */
    private Map<String, Integer> buildInDegree(Map<String, List<String>> graph) {
        Map<String, Integer> inDegree = new HashMap<>();
        for (String key : graph.keySet()) {
            if (!inDegree.containsKey(key)) {
                inDegree.put(key, 0);
            }
            for (String node : graph.get(key)) {
                if (!inDegree.containsKey(node)) {
                    inDegree.put(node, 0);
                }
                inDegree.put(node, inDegree.get(node)  +1 );
            }
        }
        return inDegree;
    }
}
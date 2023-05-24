package com.carol.niuke.top101;

import java.util.Stack;

/**
 * @author Carol
 * @date 2023/5/24
 * @since 1.0.0
 */
public class StackAndQueue {
    public static void main(String[] args) {
        StackAndQueue stackAndQueue = new StackAndQueue();
        stackAndQueue.push(1);
        stackAndQueue.push(2);
        System.out.println(stackAndQueue.pop());
        System.out.println(stackAndQueue.pop());
        stackAndQueue.push(3);
        stackAndQueue.push(4);
        System.out.println(stackAndQueue.pop());
        System.out.println(stackAndQueue.pop());
    }
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
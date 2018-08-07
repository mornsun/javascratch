package lintcode;

import java.util.*;

/**
 * As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

Have you met this question in a real interview? Yes
Example
For push(1), pop(), push(2), push(3), top(), pop(), you should return 1, 2 and 2

Challenge
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.

Tags Expand 
LintCode Copyright Stack Queue


Related Problems Expand 
Medium Min Stack 29 %

 * @author Chauncey
 *
 */
public class ImplementQueuebyTwoStacks
{
    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int element) {
        stack1.push(element);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int top() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ImplementQueuebyTwoStacks solution = new ImplementQueuebyTwoStacks();
        solution.push(1);
        System.out.println(solution.pop());
        solution.push(2);
        solution.push(3);
        System.out.println(solution.top());
        System.out.println(solution.pop());
    }

}

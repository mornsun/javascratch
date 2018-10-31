/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.

 FreqStack has two functions:

 push(int x), which pushes an integer x onto the stack.
 pop(), which removes and returns the most frequent element in the stack.
 If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.


 Example 1:

 Input:
 ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 Output: [null,null,null,null,null,null,null,5,7,5,4]
 Explanation:
 After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

 pop() -> returns 5, as 5 is the most frequent.
 The stack becomes [5,7,5,7,4].

 pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 The stack becomes [5,7,5,4].

 pop() -> returns 5.
 The stack becomes [5,7,4].

 pop() -> returns 4.
 The stack becomes [5,7].


 Note:

 Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.


 Related Topic
 Hash Table, Stack

 beat 57.83%

 * @author Chauncey
 *
 */
public class LC_895_Maximum_Frequency_Stack
{
    public class FreqStack
    {
        private final ArrayList<Stack<Integer>> stacks = new ArrayList<>();
        private final HashMap<Integer, Integer> freqTable = new HashMap<>();

        public FreqStack() {

        }

        public void push(int x) {
            Integer freq = freqTable.get(x);
            if (freq == null) {
                freq = 0;
            }
            freq += 1;
            freqTable.put(x, freq);
            if (stacks.size() < freq) {
                stacks.add(new Stack<>());
            }
            stacks.get(freq-1).push(x);
        }

        public int pop() {
            if (stacks.size() == 0) {
                return -1;
            }
            while (stacks.get(stacks.size()-1).empty()) {
                stacks.remove(stacks.size()-1);
                if (stacks.size() == 0) {
                    return -1;
                }
            }
            int x = stacks.get(stacks.size()-1).pop();
            int freq = freqTable.get(x);
            if (freq != stacks.size()) {
                return -1;
            }
            freq -= 1;
            freqTable.put(x, freq);

            return x;
        }

    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_895_Maximum_Frequency_Stack solution = new LC_895_Maximum_Frequency_Stack();
        LC_895_Maximum_Frequency_Stack.FreqStack stack = solution.new FreqStack();
        //
 //["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 //[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);
		System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

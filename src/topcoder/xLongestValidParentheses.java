/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

Hide Tags Dynamic Programming String
Hide Similar Problems (E) Valid Parentheses

best?:两遍扫描，时间复杂度O(n)，空间复杂度O(1)
 * 
 * @author Chauncey
 * Runtime: 2 ms, faster than 69.95% of Java online submissions for Longest Valid Parentheses.
 * Memory Usage: 38.8 MB, less than 90.64% of Java online submissions for Longest Valid Parentheses.
 */
public class xLongestValidParentheses
{
	public static int longestValidParentheses(String s) { //(()()(, )()())
		if (s==null || s.length()==0)
			return 0;
		LinkedList<Integer> stack = new LinkedList<>();
		int n=s.length(), max = 0;
		stack.push(-1);
		for (int i=0; i<n; ++i) {
			switch (s.charAt(i)) {
				case '(':
					stack.push(i);
					break;
				case ')':
					stack.pop();
					if (stack.isEmpty()) {
						stack.push(i);
					} else {
						max = Math.max(max, i-stack.peek());
					}
					break;
			}
		}
		return max;
	}

	public static int longestValidParentheses_2scanO1_wrong(String s) { //(()()(, )()()), "))))())()()(()"
		if (s == null || s.length() == 0)
			return 0;
		int n = s.length();
		int stacked = 1, last = -1, max_ltor = 0;
		for (int i = 0; i < n; ++i) {
			switch (s.charAt(i)) {
				case '(':
					stacked++;
					break;
				case ')':
					stacked--;
					if (stacked == 0) {
						stacked = 1;
						last = i;
					} else {
						max_ltor = Math.max(max_ltor, i - last);
					}
			}
		}
		stacked = 1; last = n;
		int max_rtol = 0;
		for (int i = n-1; i >= 0; --i) {
			switch (s.charAt(i)) {
				case ')':
					stacked++;
					break;
				case '(':
					stacked--;
					if (stacked == 0) {
						stacked = 1;
						last = i;
					} else {
						max_rtol = Math.max(max_rtol, last - i);
					}
			}
		}
		return Math.min(max_ltor, max_rtol);
	}

    public static int longestValidParentheses1(String s) {
    	Stack<Integer> stack = new Stack<Integer>();
    	int len = s.length();
    	int max = 0;
    	int last = -1;
    	int l = -1;
    	for (int i=0; i<len; ++i) {
    		char ch = s.charAt(i);
    		switch(ch) {
    		case '(':
    			if (l == -1) {
    				stack.push(i);
    			} else {
    				stack.push(l);
    				l = -1;
    			}
    			break;
    		case ')':
    			if (stack.isEmpty()) {
    				last = i;
    				l = -1;
    			} else {
    				int left = stack.pop();
    				if (stack.isEmpty()) {
    					if (i-last > max) {
    						max = i-last;
    					}
    					l = last+1;
    				} else {
        				if (i-left > max) {
        					max = i-left+1;
        				}
    					l = left;
    				}
    			}
    			break;
    		}
    	}
    	return max;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(longestValidParentheses("())")); //2
		System.out.println(longestValidParentheses("(()")); //2
		System.out.println(longestValidParentheses(")()())")); //4
		System.out.println(longestValidParentheses("(()()(")); //4
		System.out.println(longestValidParentheses("))))())()()(()")); //4
	}

}

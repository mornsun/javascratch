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

best:两遍扫描，时间复杂度O(n)，空间复杂度O(1)
 * 
 * @author Chauncey
 *
 */
public class xLongestValidParentheses
{
    public static int longestValidParentheses(String s) {
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
		System.out.println(longestValidParentheses("(()()"));
	}

}

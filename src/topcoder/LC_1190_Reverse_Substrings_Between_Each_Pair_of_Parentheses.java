/**
 * 
 */
package topcoder;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 	You are given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 *
 * Example 1:
 *
 * Input: s = "(abcd)"
 * Output: "dcba"
 *
 * Example 2:
 *
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 *
 * Example 3:
 *
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
 *
 * Example 4:
 *
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 *
 * Constraints:
 *
 * 0 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It's guaranteed that all parentheses are balanced.
 *
 * @author Chauncey
 * Runtime: 3 ms, faster than 49.19% of Java online submissions for Reverse Substrings Between Each Pair of Parentheses.
 * Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Reverse Substrings Between Each Pair of Parentheses.
 * Runtime: 1 ms, faster than 99.27% of Java online submissions for Reverse Substrings Between Each Pair of Parentheses.
 * Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Reverse Substrings Between Each Pair of Parentheses.
 */
public class LC_1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses
{
	public String reverseParentheses(String s) {
		int n = s.length();
		LinkedList<Integer> opened = new LinkedList<>();
		int[] pair = new int[n];
		for (int i = 0; i < n; ++i) {
			if (s.charAt(i) == '(')
				opened.push(i);
			if (s.charAt(i) == ')') {
				int j = opened.pop();
				pair[i] = j;
				pair[j] = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0, d = 1; i < n; i += d) {
			if (s.charAt(i) == '(' || s.charAt(i) == ')') {
				i = pair[i];
				d = -d;
			} else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}

	public String reverseParentheses1(String s) {

		if (s==null || s.length()==0)
			return "";

		int n = s.length();

		LinkedList<LinkedList<Character>> stk = new LinkedList<>();
		LinkedList<Character> curr = new LinkedList<>();
		for (int i=0; i<n; ++i) {
			switch(s.charAt(i)) {
				case '(':
					stk.push(curr);
					curr = new LinkedList<>();
					break;
				case ')':
					Collections.reverse(curr);
					LinkedList<Character> top = curr;
					curr = stk.pop();
					curr.addAll(top);
					break;
				default:
					curr.add(s.charAt(i));
					break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (char ch : curr)
			sb.append(ch);
		return sb.toString();
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses solution = new LC_1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses();
        System.out.println(solution.reverseParentheses("(abcd)")); //"dcba"
		System.out.println(solution.reverseParentheses("(u(love)i)")); //"iloveu"
		System.out.println(solution.reverseParentheses("(ed(et(oc))el)")); //"leetcode"
		System.out.println(solution.reverseParentheses("a(bcdefghijkl(mno)p)q")); //"apmnolkjihgfedcbq"
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

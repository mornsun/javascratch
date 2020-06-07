/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 *
 * Note:
 *     The length of num is less than 10002 and will be ≥ k.
 *     The given num does not contain any leading zero.
 *
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 *
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 *
 * @author Chauncey
 * Runtime: 2 ms, faster than 98.91% of Java online submissions for Remove K Digits.
 * Memory Usage: 38.6 MB, less than 9.09% of Java online submissions for Remove K Digits.
 */
public class LC_402_Remove_K_Digits
{
    public String removeKdigits(String num, int k) {
        if (num==null || num.length()==0 || k>num.length())
            return null;

        int n = num.length();
        LinkedList<Character> stack = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        int i;

        for (i=0; i<n; ++i) {
            if (k==0) {
                break;
            }
            char c = num.charAt(i);
            while (!stack.isEmpty() && c < stack.peek()) {
                stack.pop();
                if (--k==0) break;
            }
            stack.push(c);
        }

        while(k-->0 && !stack.isEmpty()) {
            stack.pop();
        }
        while(!stack.isEmpty()) {
            res.append(stack.pop());
        }
        res.reverse();

        if (i<n)
            res.append(num.substring(i));

        while (res.length() > 0 && res.charAt(0)=='0')
            res.deleteCharAt(0);

        return res.length()==0 ? "0" : res.toString();
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_402_Remove_K_Digits solution = new LC_402_Remove_K_Digits();
		System.out.println(solution.removeKdigits("1432219", 3)); //"1219"
        System.out.println(solution.removeKdigits("10200", 1)); //"200"
        System.out.println(solution.removeKdigits("10", 2)); //"0"
        System.out.println(solution.removeKdigits("10", 1)); //"0"

        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

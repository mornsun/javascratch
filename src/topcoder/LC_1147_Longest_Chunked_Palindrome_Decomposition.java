/**
 * 
 */
package topcoder;

import java.util.ArrayList;

/**
 * 	Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:
 *
 * Each a_i is a non-empty string;
 * Their concatenation a_1 + a_2 + ... + a_k is equal to text;
 * For all 1 <= i <= k,  a_i = a_{k+1 - i}.
 *
 * Example 1:
 *
 * Input: text = "ghiabcdefhelloadamhelloabcdefghi"
 * Output: 7
 * Explanation: We can split the string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".
 * Example 2:
 *
 * Input: text = "merchant"
 * Output: 1
 * Explanation: We can split the string on "(merchant)".
 * Example 3:
 *
 * Input: text = "antaprezatepzapreanta"
 * Output: 11
 * Explanation: We can split the string on "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".
 * Example 4:
 *
 * Input: text = "aaa"
 * Output: 3
 * Explanation: We can split the string on "(a)(a)(a)".
 *
 * Constraints:
 *
 * text consists only of lowercase English characters.
 * 1 <= text.length <= 1000
 *
 * Related Topic:
 * Array

 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Chunked Palindrome Decomposition.
 * Memory Usage: 35.3 MB, less than 100.00% of Java online submissions for Longest Chunked Palindrome
 */
public class LC_1147_Longest_Chunked_Palindrome_Decomposition
{
    public int longestDecomposition(String text) {
        if (text==null || text.length()==0)
            return 0;

        int n=text.length();
        int i=1;
        while (i<=n/2) {
            int j=0;
            for (; j<i; ++j) {
                if (text.charAt(j) != text.charAt(n-i+j))
                    break;
            }
            if (i==j)
                return longestDecomposition(text.substring(i, n-i)) + 2;
            ++i;
        }
        return 1;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1147_Longest_Chunked_Palindrome_Decomposition solution = new LC_1147_Longest_Chunked_Palindrome_Decomposition();
        System.out.println(solution.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi")); //7
        System.out.println(solution.longestDecomposition("merchant")); //1
        System.out.println(solution.longestDecomposition("antaprezatepzapreanta")); //11
        System.out.println(solution.longestDecomposition("aaa")); //3
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

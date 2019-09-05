/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 	Given a string s, we make queries on substrings of s.
 * For each query queries[i] = [left, right, k], we may rearrange the substring s[left], ..., s[right], and then choose up to k of them to replace with any lowercase English letter.
 * If the substring is possible to be a palindrome string after the operations above, the result of the query is true. Otherwise, the result is false.
 * Return an array answer[], where answer[i] is the result of the i-th query queries[i].
 * Note that: Each letter is counted individually for replacement so if for example s[left..right] = "aaa", and k = 2, we can only replace two of the letters.  (Also, note that the initial string s is never modified by any query.)
 *
 * Example :
 *
 * Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
 * Output: [true,false,false,true,true]
 * Explanation:
 * queries[0] : substring = "d", is palidrome.
 * queries[1] : substring = "bc", is not palidrome.
 * queries[2] : substring = "abcd", is not palidrome after replacing only 1 character.
 * queries[3] : substring = "abcd", could be changed to "abba" which is palidrome. Also this can be changed to "baab" first rearrange it "bacd" then replace "cd" with "ab".
 * queries[4] : substring = "abcda", could be changed to "abcba" which is palidrome.
 *
 * Constraints:
 *
 * 1 <= s.length, queries.length <= 10^5
 * 0 <= queries[i][0] <= queries[i][1] < s.length
 * 0 <= queries[i][2] <= s.length
 * s only contains lowercase English letters.
 *
 * @author Chauncey
 * Runtime: 70 ms, faster than 37.76% of Java online submissions for Can Make Palindrome from Substring.
 * Memory Usage: 111.4 MB, less than 100.00% of Java online submissions for Can Make Palindrome from Substring.
 */
public class LC_1177_Can_Make_Palindrome_from_Substring
{
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {

        ArrayList<Boolean> res = new ArrayList<Boolean>();
        if (queries==null || queries.length==0)
            return res;

        if (s==null || s.length()==0)
            return Arrays.asList(new Boolean[queries.length]);

        int n = s.length();
        int[][] cnts = new int[n+1][26];
        for (int i=0; i<n; ++i) {
            for (int j=0; j<26; ++j) {
                cnts[i+1][j] = cnts[i][j];
            }
            int idx = s.charAt(i)-'a';
            cnts[i+1][idx]++;
        }

        for (int i=0; i<queries.length; ++i) {
            int l = queries[i][0], r = queries[i][1], k = queries[i][2], c=0;
            for (int j=0; j<26; ++j) {
                if ((cnts[r+1][j] - cnts[l][j] & 1) == 1) {
                    c++;
                }
            }
            res.add(c>k*2+1 ? false : true);
        }

        return res;
    }

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1177_Can_Make_Palindrome_from_Substring solution = new LC_1177_Can_Make_Palindrome_from_Substring();
		//[1,2,-3,3,1]
        System.out.println(solution.canMakePaliQueries("abcda", new int[][]{{3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}})); //[true,false,false,true,true]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

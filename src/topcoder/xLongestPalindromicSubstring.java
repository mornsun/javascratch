/**
 * 
 */
package topcoder;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"

Hide Tags String, DP
Hide Similar Problems (H) Shortest Palindrome

 How can we reuse a previously computed palindrome to compute a larger palindrome?
 If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?
 Complexity based hint:
 If we use brute-force and check whether for every start and end position a substring is a palindrome we have O(n^2) start - end pairs and O(n) palindromic checks. Can we reduce the time for palindromic checks to O(1) by reusing some previous computation.

中心找两边 O(n2)
f[i][j] = true ; i = j
			S[i] = S[j] ; j = i + 1
			S[i] = S[j] and f[i+1][j-1] ; j > i + 1

类似KMP，Manacher’s Algorithm, 参考题目shortest Palindrome O(n)
http://blog.csdn.net/hopeztm/article/details/7932245
http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html

 * @author Chauncey
 * Runtime: 23 ms, faster than 90.36% of Java online submissions for Longest Palindromic Substring.
 * Memory Usage: 39.6 MB, less than 42.12% of Java online submissions for Longest Palindromic Substring.
 */
public class xLongestPalindromicSubstring {

	public static String longestPalindrome(String s) {
		if (s==null) {
			return "";
		}
		int n=s.length(), max = 0;
		String res = "";
		for (int i=0; i<n; ++i) {
			int j;
			// center at i
			for (j=1; i-j>=0 && i+j<n && s.charAt(i-j)==s.charAt(i+j); ++j);
			int len = 1 + (j-1<<1);
			if (len > max) {
				max = len;
				res = s.substring(i-j+1, i+j);
			}
			//center at i+0.5
			if (i==n-1 || s.charAt(i)!=s.charAt(i+1)) continue;
			for (j=1; i-j>=0 && i+1+j<n && s.charAt(i-j)==s.charAt(i+1+j); ++j);
			len = 2 + (j-1<<1);
			if (len > max) {
				max = len;
				res = s.substring(i-j+1, i+1+j);
			}
		}
		return res;
	}

    public static String longestPalindrome_DP(String s) {
    	if (s==null || "".equals(s)) return "";
    	final int slen = s.length();
        boolean[][] dp = new boolean[slen][slen];
        int max = 1; //max points at the 1st character
        int max_begin = 0;
        int max_end = 0;
        for (int j=0; j<slen; ++j) {
        	for (int i=0; i<=j; ++i) {
        		if (i==j) {
        			dp[i][j] = true;
        		} else if (j==i+1 || dp[i+1][j-1]) {
        			if (s.charAt(i) == s.charAt(j)) {
        				dp[i][j] = true;
        				int l = j-i+1;
        				if (l>max) {
        					max = l;
        					max_begin = i;
        					max_end = j;
        				}
        			} else
        				dp[i][j] = false;
        		} else {
        			dp[i][j] = false;
        		}
        	}
        }
        /*for (int j=0; j<slen; ++j) {
        	for (int i=0; i<j; ++i) {
        		System.out.print(i+":"+j+":"+dp[i][j] +"\t");
        	}
        	System.out.println();
        }
        System.out.println(max);*/
        return s.substring(max_begin, max_end+1);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(longestPalindrome("babad")); //aba, bab
		System.out.println(longestPalindrome("cbbd")); //bb
		System.out.println(longestPalindrome("aaabcdcbcdha"));
	}

}

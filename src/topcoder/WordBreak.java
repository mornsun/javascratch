/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

Hide Tags Dynamic Programming
Hide Similar Problems (M) Palindrome Partitioning (H) Word Break II

 * @author Chauncey
 * Runtime: 9 ms, faster than 18.65% of Java online submissions for Word Break.
 * Memory Usage: 39 MB, less than 83.38% of Java online submissions for Word Break.
 * Runtime: 2 ms, faster than 92.64% of Java online submissions for Word Break.
 * Memory Usage: 38.6 MB, less than 92.33% of Java online submissions for Word Break.
 */
public class WordBreak
{
	public static boolean wordBreak(String s, Set<String> wordDict) {
		if (s==null || wordDict==null)
			return false;
		int n = s.length();
		boolean[] dp = new boolean[n+1];
		dp[0] = true;
		for (int i=1; i<=n; ++i) {
			for (int j=i-1; j>=0; --j) {
				if (dp[j] && wordDict.contains(s.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}

	public static boolean wordBreak2(String s, Set<String> wordDict) {
		if (s==null || wordDict==null)
			return false;
		int n = s.length();
		Set<Integer> dp = new HashSet<>();
		dp.add(0);
		for (int i=1; i<=n; ++i) {
			boolean can = false;
			for (int begin : dp) {
				if (wordDict.contains(s.substring(begin, i))) {
					can = true;
					break;
				}
			}
			if (can)
				dp.add(i);
		}
		return dp.contains(n);
	}

    // dp[j] = dp[i-1] && (s[i-1,j] is in dict)
    public static boolean wordBreak1(String s, Set<String> wordDict) {
        if (null == wordDict || wordDict.isEmpty()) {
        	return (null == s || s.isEmpty());
        }
        if (null == s || s.isEmpty()) return true;
        int len = s.length();
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for (int i=1; i<=len; ++i) {
        	if (!dp[i-1])
        		continue;
    		String word = s.substring(i-1, len);
    		if (wordDict.contains(word)) {
        		return true;
    		}
        	for (int j=len-1; j>=i; --j) {
        		word = s.substring(i-1, j);
        		if (wordDict.contains(word)) {
        			dp[j] = true;
        		}
        	}
        }
        return false;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(wordBreak("leetcode", new HashSet<>(Arrays.asList(new String[]{"leet","code"}))));
		System.out.println(wordBreak("applepenapple", new HashSet<>(Arrays.asList(new String[]{"apple","pen"}))));
		System.out.println(wordBreak("catsandog", new HashSet<>(Arrays.asList(new String[]{"cats","dog","sand","and","cat"}))));
	}

}

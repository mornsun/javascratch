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
 *
 */
public class WordBreak
{
    // dp[j] = dp[i-1] && (s[i-1,j] is in dict)
    public static boolean wordBreak(String s, Set<String> wordDict) {
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
		String[] strs = new String[]{"leet","code"};
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("a");
		System.out.println(wordBreak("a",wordDict));
	}

}

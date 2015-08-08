/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class Anagrams
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

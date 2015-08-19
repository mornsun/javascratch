/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

Hide Tags Dynamic Programming Backtracking
Hide Similar Problems (M) Word Break

 * @author Chauncey
 *
 */
public class xWordBreakII
{
    // dp[j] = dp[i-1] && (s[i-1,j] is in dict)
    public static List<String> wordBreak(String s, Set<String> wordDict) {
        if (null == wordDict || wordDict.isEmpty()) {
        	return new ArrayList<String>();
        }
        if (null == s || s.isEmpty()) return new ArrayList<String>();
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i=1; i<=len; ++i) {
        	if (!dp[i-1])
        		continue;
        	for (int j=len; j>=i; --j) {
        		String word = s.substring(i-1, j);
        		if (wordDict.contains(word)) {
        			dp[j] = true;
        		}
        	}
        }
    	if (!dp[len])
    		return new ArrayList<String>();
    	
    	final ArrayList<List<String>> res = new ArrayList<List<String>>();
    	for (int i=len-1; i>=0; --i) {
			final List<String> v = new ArrayList<String>();
    		res.add(v);
    	}
    	//List<String> cur = new LinkedList<String>();
    	for (int i=len-1; i>=0; --i) {
    		if (dp[i])
    		for (int j=i; j<len; ++j) {
    			if (dp[j+1] && wordDict.contains(s.substring(i, j+1))) {
    				final String palindrome = s.substring(i, j+1);
					//System.out.println(i+":"+j+":"+palindrome);
    				if (j+1 < len) {
    					//System.out.println("in"+(j+1)+":"+res.get(j+1));
    					for (String v: res.get(j+1)) {
    						final String nv = palindrome+" "+v;
    						res.get(i).add(nv);
    					}
    					//System.out.println("out"+(j+1)+":"+res);
    				} else {
    					final String v = palindrome;
						res.get(i).add(v);
    				}
    			}
    		}
    	}
		//System.out.println(res);
    	//Collections.reverse(res.get(0));
    	return res.get(0);
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String[] strs = new String[]{"leet","code"};
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("leet");
		wordDict.add("code");
		System.out.println(wordBreak("leetcode", wordDict));
		wordDict = new HashSet<String>();
		wordDict.add("a");
		wordDict.add("aa");
		wordDict.add("aaa");
		wordDict.add("aaaa");
		wordDict.add("aaaaa");
		wordDict.add("aaaaaa");
		wordDict.add("aaaaaaa");
		wordDict.add("aaaaaaaa");
		wordDict.add("aaaaaaaaa");
		wordDict.add("aaaaaaaaaa");
		System.out.println(wordBreak("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",wordDict));
		//, ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
	}

}

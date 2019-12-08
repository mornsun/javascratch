/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []

Hide Tags Dynamic Programming Backtracking
Hide Similar Problems (M) Word Break

 * @author Chauncey
 * Runtime: 6 ms, faster than 60.93% of Java online submissions for Word Break II.
 * Memory Usage: 40.5 MB, less than 9.84% of Java online submissions for Word Break II.
 */
public class xWordBreakII
{
	public List<String> wordBreak(String s, List<String> wordDict) {
		HashMap<String, List<String>> map = new HashMap<>();
		HashSet<String> words = new HashSet<>(wordDict);
		return dfs(s, words, map);
	}

	private List<String> dfs(String s, HashSet<String> words, HashMap<String, List<String>> map) {
		List<String> res = map.get(s);
		if (res!=null) return res;
		res = new ArrayList<>();
		if (s.length()==0) {
			res.add(null);
			return res;
		}
		for (int len=1; len<=s.length(); ++len) {
			String w = s.substring(0,len);
			if (!words.contains(w)) continue;
			for (String tail : dfs(s.substring(len), words, map))
				res.add(w+(tail==null?"" : " "+tail));
		}
		map.put(s, res);
		return res;
	}

    // dp[j] = dp[i-1] && (s[i-1,j] is in dict)
    public static List<String> wordBreak1(String s, Set<String> wordDict) {
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
		xWordBreakII solution = new xWordBreakII();
		System.out.println(solution.wordBreak("leetcode", Arrays.asList(new String[]{"leet", "code"})));
		System.out.println(solution.wordBreak("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",Arrays.asList(new String[]{"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"})));
		//, ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
	}

}

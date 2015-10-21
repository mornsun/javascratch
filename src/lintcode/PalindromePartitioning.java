/**
 * 
 */
package lintcode;

import java.util.*;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Have you met this question in a real interview? Yes
Example
Given s = "aab",

Return:

[
  ["aa","b"],
  ["a","a","b"]
]
Tags Expand 
Backtracking Depth First Search


Related Problems Expand 
Medium Palindrome Partitioning II 20 %

 * @author Chauncey
 *
 */
public class PalindromePartitioning
{
    /**
     * @param s: A string
     * @return: A list of lists of string
     * 
     * dp[i,j] = s[i]==s[j] && (dp[i+1][j-1] || j-i<=2)
     */
    public List<List<String>> partition(String s) {
    	ArrayList<List<String>> res = new ArrayList<List<String>>();
    	if (null == s || s.length()==0) return res;
    	int sz = s.length();
    	boolean[][] dp = new boolean[sz][sz];
    	for (int i=sz-1; i>=0; --i) {
    		dp[i][i] = true;
    		for (int j=i+1; j<sz; ++j) {
    			dp[i][j] = s.charAt(i) == s.charAt(j) && (j-i<=2 || dp[i+1][j-1]);
    		}
    	}
    	LinkedList<String> path = new LinkedList<String>();
    	dfs(res, path, s, 0, dp);
    	return res;
    }
    private final void dfs(ArrayList<List<String>> res, LinkedList<String> path,
    		String s, int idx, boolean[][] dp) {
    	if (idx == s.length()) {
    		res.add(new LinkedList<String>(path));
    		return;
    	}
    	int sz = s.length();
    	for (int j=sz-1; j>=idx; --j) {
    		if (dp[idx][j]) {
    			path.addLast(s.substring(idx, j+1));
    			dfs(res, path, s, j+1, dp);
    			path.removeLast();
    		}
    	}
    }
    
    // dp[i,j] = s[i]==s[j] && (dp[i+1][j-1] || j-i<1)
	public List<List<String>> partition1(String s) {
    	final int len = s.length();
    	final boolean[][] dp = new boolean [len][len];
    	for (int i=len-1; i>=0; --i) {
    		dp[i][i] = true;
    		for (int j=i+1; j<len; ++j) {
    			dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j-i<=2 || dp[i+1][j-1]);
    		}
    	}
    	final ArrayList<List<List<String>>> res = new ArrayList<List<List<String>>>();
    	for (int i=len-1; i>=0; --i) {
			final ArrayList<List<String>> v = new ArrayList<List<String>>();
    		res.add(v);
    	}
    	//List<String> cur = new LinkedList<String>();
    	for (int i=len-1; i>=0; --i) {
    		for (int j=i; j<len; ++j) {
    			if (dp[i][j]) {
    				final String palindrome = s.substring(i, j+1);
					System.out.println(i+":"+j+":"+palindrome);
    				if (j+1 < len) {
    					System.out.println("in"+(j+1)+":"+res.get(j+1));
    					for (List<String> v: res.get(j+1)) {
    						final ArrayList<String> nv = new ArrayList<String>(v);
    						nv.add(0,palindrome);
    						res.get(i).add(nv);
    					}
    					System.out.println("out"+(j+1)+":"+res);
    				} else {
    					final ArrayList<String> v = new ArrayList<String>();
						v.add(palindrome);
						res.get(i).add(v);
    				}
    			}
    		}
    	}
		//System.out.println(res);
    	Collections.reverse(res.get(0));
    	return res.get(0);
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PalindromePartitioning solution = new PalindromePartitioning();
		System.out.println(solution.partition("aab"));
	}

}

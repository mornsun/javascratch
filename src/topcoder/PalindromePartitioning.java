/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class PalindromePartitioning
{
    // dp[i,j] = s[i]==s[j] && (dp[i+1][j-1] || j-i<1)
	public static List<List<String>> partition(String s) {
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
		int[] nums = new int[]{-2,1};
		System.out.println(partition("aab"));
	}

}

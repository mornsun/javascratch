package topcoder;

import java.util.*;

/**
 * Given a string S, find the length of the longest substring T that contains at most two distinct characters.
For example,
Given S = “eceba”,
T is “ece” which its length is 3.

Google Round 1
Example: "ababcbcbaaabbdef" 
has a 2-character substring "baaabb" of length 6 (longest) 
and a 2-character substring "bcbcb" of lenght 5 (second longest).

 * @author Chauncey
 *
 */
public class LongestSubstringwithAtMostTwoDistinctCharacters
{
    public String lengthOfLongestSubstringTwoDistinct(String s) {
        int i = 0, j = -1, maxLen = 0, maxL = 0, maxR = 0;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) == s.charAt(k - 1)) continue;
            if (j >= 0 && s.charAt(j) != s.charAt(k)) {
            	if (k - i > maxLen) {
            		maxLen = k - i;
            		maxL = i;
            		maxR = k;
            	}
                i = j + 1; 
            }
            j = k - 1;  
        }
    	if (s.length() - i > maxLen) {
    		maxLen = s.length() - i;
    		maxL = i;
    		maxR = s.length();
    	}
        return s.substring(maxL, maxR);
    }

    /**
     * Google Round 1 JAVA Answer for k
     * @param s
     * @param k
     * @return
     */
    public String lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k < 1) return "";
        int slen = s.length();
        if (slen == 0) return "";
        int[] hits = new int[256];
        int nhit = 0;
        int lo = 0;
        int hi = 0;
        int maxwin = 0;
        int lwin = 0;
        int rwin= 0;
        while (lo <= hi) {
            if (nhit <= k) {
                if (hi >= slen) break;
                char ch = s.charAt(hi);
                if (hits[ch] == 0)
                    ++nhit;
                ++hits[ch];
                ++hi;
            } else {
                if (hi-lo-1 > maxwin) {
                    maxwin = hi - lo - 1;
                    lwin = lo;
                    rwin = hi-1;
                }
                if (lo >= slen) break;
                char ch = s.charAt(lo);
                --hits[ch];
                if (hits[ch] == 0)
                    --nhit;
                ++lo;
            }
        }
        if (nhit <= k) {
            if (hi-lo > maxwin) {
                maxwin = hi - lo;
                lwin = lo;
                rwin = hi;
            }
        }
        return s.substring(lwin, rwin);
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LongestSubstringwithAtMostTwoDistinctCharacters solution = new LongestSubstringwithAtMostTwoDistinctCharacters();
		
		System.out.println(solution.lengthOfLongestSubstringTwoDistinct("ababcbcbaaabbdef"));
		System.out.println(solution.lengthOfLongestSubstringKDistinct("ababcbcbaaabbdef", 5));
		System.out.println(solution.lengthOfLongestSubstringTwoDistinct("aaaaaa"));
		System.out.println(solution.lengthOfLongestSubstringKDistinct("aaaaaa", 5));
		System.out.println(solution.lengthOfLongestSubstringTwoDistinct("abcdef"));
		System.out.println(solution.lengthOfLongestSubstringKDistinct("abcdef", 5));
	}

}

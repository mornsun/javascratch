package topcoder;

import java.util.*;

/**
 * Problem Description:

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:

pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false. 
Notes:
You may assume both pattern and str contains only lowercase letters.

 * Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dogcatcatdog" should return true.
pattern = "abba", str = "dogcatcatfish" should return false.
pattern = "aaaa", str = "dogcatcatdog" should return false.
pattern = "abba", str = "dogdogdogdog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

Credits:
Special thanks to @minglotus6 for adding this problem and creating all test cases.

Hide Tags Hash Table
Hide Similar Problems (E) Isomorphic Strings (H) Word Pattern II

 * @author Chauncey
 *
 */
public class WordPatternII
{
    public boolean wordPatternMatch(String pattern, String str) {  
        if (null == pattern || null == str) return pattern==str;
        HashMap<String, Character> map = new HashMap<String, Character>();
        boolean[] used = new boolean[256];
        return dfs_search(pattern, 0, str, 0, map, used);
    }
    
    private final boolean dfs_search(String pattern, int pi, String str, int si,
    		HashMap<String, Character> map, boolean[] used) {
    	if (si == str.length() || pi == pattern.length()) {
    		if (si == str.length() && pi == pattern.length()) {
	    		//System.out.println(map);
	    		return true;
    		}
    		return false;
    	}
    	int se = str.length() - pattern.length() + pi;
        for (int i=si; i<=se; ++i) {
        	String cur = str.substring(si, i+1);
        	Character ch = map.get(cur);
        	char c = pattern.charAt(pi);
        	if (null == ch) {
        		if (used[c])
        			continue;
        		map.put(cur, c);
        		used[c] = true;
            	if (dfs_search(pattern, pi+1, str, i+1, map, used))
            		return true;
            	map.remove(cur);
        		used[c] = false;
        	} else {
        		if (ch != c)
        			continue;
            	if (dfs_search(pattern, pi+1, str, i+1, map, used))
            		return true;
        	}
        }
        return false;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WordPatternII solution = new WordPatternII();
		System.out.println(solution.wordPatternMatch("abba", "dogcatcatdog")); //true
		System.out.println(solution.wordPatternMatch("abba", "dogcatcatfish")); //false
		System.out.println(solution.wordPatternMatch("aaaa", "dogcatcatdog")); //false
		System.out.println(solution.wordPatternMatch("abba", "dogdogdogdog")); //false
		System.out.println(solution.wordPatternMatch("abab", "redblueredblue")); //true
		System.out.println(solution.wordPatternMatch("abcb", "redblueredblue")); //true
		System.out.println(solution.wordPatternMatch("aaaa", "asdasdasdasd")); //true
		System.out.println(solution.wordPatternMatch("aabb", "xyzabcxzyabc")); //true
	}

}

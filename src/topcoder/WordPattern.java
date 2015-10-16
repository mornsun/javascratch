package topcoder;

import java.util.*;

/**
 * Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

Credits:
Special thanks to @minglotus6 for adding this problem and creating all test cases.

Hide Tags Hash Table
Hide Similar Problems (E) Isomorphic Strings (H) Word Pattern II

 * @author Chauncey
 *
 */
public class WordPattern
{
    public boolean wordPattern(String pattern, String str) {
        if (null == pattern || null == str) return pattern==str;
        String[] words = str.trim().split(" ");
        if (pattern.length() != words.length) return false;
        HashMap<String, Character> map = new HashMap<String, Character>();
        boolean[] used = new boolean[256];
        for (int i=0; i<words.length; ++i) {
        	Character ch = map.get(words[i]);
        	if (null == ch) {
        		if (used[pattern.charAt(i)])
        			return false;
        		map.put(words[i], pattern.charAt(i));
        		used[pattern.charAt(i)] = true;
        	} else {
        		if (ch != pattern.charAt(i))
        			return false;
        	}
        }
        return true;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WordPattern solution = new WordPattern();
		System.out.println(solution.wordPattern("abba", "dog cat cat dog"));
		System.out.println(solution.wordPattern("abba", "dog cat cat fish"));
		System.out.println(solution.wordPattern("aaaa", "dog cat cat dog"));
		System.out.println(solution.wordPattern("aaaa", "dog dog dog dog"));
		System.out.println(solution.wordPattern("abba", "dog dog dog dog"));
	}

}

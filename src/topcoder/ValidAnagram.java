/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class ValidAnagram
{
    public static boolean isAnagram(String s, String t) {
    	if (s == null || t == null || s.length() != t.length()) return false;
    	int[] flag = new int[26];
    	int len = s.length();
    	for (int i=0; i<len; ++i) {
    		int idx = s.charAt(i)-'a';
    		++flag[idx];
    	}
    	for (int i=0; i<len; ++i) {
    		int idx = t.charAt(i)-'a';
    		--flag[idx];
    	}
    	for (int i=0; i<flag.length; ++i) {
    		if (flag[i] != 0)
    			return false;
    	}
        return true;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(isAnagram("haha","ahah"));
	}

}

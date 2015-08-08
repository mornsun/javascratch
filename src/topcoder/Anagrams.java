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
    public static List<String> anagrams(String[] strs) {
    	List<String> res = new LinkedList<String>();
        if (strs == null || strs.length == 0) return res;
        HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>((int)(strs.length/0.75)+1);
        for (String str : strs) {
        	int len = str.length();
        	char[] keych = new char[str.length()];
        	for (int i=0; i<len; ++i) {
        		keych[i] = str.charAt(i);
        	}
        	Arrays.sort(keych);
        	String key = new String(keych);
        	LinkedList<String> anagrams = map.get(key);
        	if (anagrams == null) {
        		anagrams = new LinkedList<String>();
        		anagrams.add(str);
        		map.put(key, anagrams);
        	} else {
        		anagrams.add(str);
        	}
        }
        for (LinkedList<String> anagrams : map.values()) {
        	if (anagrams.size() > 1) {
        		for (String str : anagrams)
        			res.add(str);
        	}
        }
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String[] strs = new String[]{"",""};
		System.out.println(anagrams(strs));
	}

}

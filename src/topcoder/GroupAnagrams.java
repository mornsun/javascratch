/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
Update (2015-08-09):
The signature of the function had been updated to return list<list<string>> instead of list<string>, as suggested here. If you still see your function signature return a list<string>, please click the reload button  to reset your code definition.

Hide Tags Hash Table String
Hide Similar Problems (E) Valid Anagram (E) Group Shifted Strings

 * @author Chauncey
 *
 */
public class GroupAnagrams
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

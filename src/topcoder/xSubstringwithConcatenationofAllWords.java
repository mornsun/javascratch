/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

Hide Tags Hash Table Two Pointers String
Hide Similar Problems (H) Minimum Window Substring

 * @author Chauncey
 *
 */
public class xSubstringwithConcatenationofAllWords
{
	/**
	 * Use slide window to cache probable valid solution
	 * time complexity O(n)
	 * space complexity O(m)
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
    public static List<Integer> findSubstring(String s, String[] words) {
        if (words == null || words.length == 0 || s.length() == 0) return null;
        int wordlen = words[0].length();
        HashMap<String, Integer> map = new HashMap<String, Integer>(words.length*4/3+1);
        for (String word : words) {
        	Integer cnt = map.get(word);
        	if (cnt == null)
        		map.put(word, 1);
        	else
        		map.put(word, cnt+1);
        }
        int slen = s.length();
        LinkedList<Integer> res = new LinkedList<Integer>();
        //sliding window
        for (int i=0; i<wordlen; ++i) {
        	int lo = i;
        	int hi = i;
            int nhit = 0;
            HashMap<String, Integer> hitmap = new HashMap<String, Integer>(words.length*4/3+1);
        	while (lo <= hi && hi <= slen) {
        		//System.out.println(lo+":"+hi+":"+nhit+":"+slen);
        		if (nhit != words.length) {
        			if (hi > slen-wordlen)
        				break;
	        		String word = s.substring(hi, hi+wordlen);
	        		//System.out.println(lo+":"+hi+":"+nhit+":"+word+"1");
	        		Integer cnt = map.get(word);
		        	if(cnt == null) {
		        		lo = hi + wordlen;
		        		nhit = 0;
		        		hitmap = new HashMap<String, Integer>(words.length*4/3+1);
		        	} else {
		        		Integer hitcnt = hitmap.get(word);
		        		if (hitcnt == null) {
		        			hitmap.put(word, 1);
			        		++nhit;
		        		} else if (hitcnt >= cnt) {
		        			String lword = s.substring(lo, lo+wordlen);
		        			while (!word.equals(lword)) {
		        				lo += wordlen;
				        		hitcnt = hitmap.get(lword);
				        		hitmap.put(lword, hitcnt-1);
				        		lword = s.substring(lo, lo+wordlen);
				        		--nhit;
		        			}
			        		lo += wordlen;
		        		} else {
		        			hitmap.put(word, hitcnt+1);
			        		++nhit;
		        		}
		        	}
		        	hi += wordlen;
        		} else {
        			res.add(lo);
        			if (lo > slen-wordlen)
        				break;
	        		String word = s.substring(lo, lo+wordlen);
	        		//System.out.println(lo+":"+hi+":"+nhit+":"+word+"2");
		        	Integer hitcnt = hitmap.get(word);
		        	if (hitcnt == null) {
		        		//error
		        	} else {
		       			hitmap.put(word, hitcnt-1);
		        		nhit--;
	        		}
		        	lo += wordlen;
        		}
        	}
        }
        Collections.sort(res);
        return res;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//"wordgoodgoodgoodbestword", ["word","good","best","good"]
		System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
	}

}

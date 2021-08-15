/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 *
 * Example 1:
 *
 * Input:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 *
 * Input:
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * Output: []

Hide Tags Hash Table, Two Pointers, String
Hide Similar Problems (H) Minimum Window Substring

 * @author Chauncey
 * Runtime: 8 ms, faster than 92.91% of Java online submissions for Substring with Concatenation of All Words.
 * Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Substring with Concatenation of All Words.
 * Runtime: 8 ms, faster than 92.91% of Java online submissions for Substring with Concatenation of All Words.
 * Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Substring with Concatenation of All Words.
 * Runtime: 85 ms, faster than 77.20% of Java online submissions for Substring with Concatenation of All Words.
 * Memory Usage: 39.6 MB, less than 58.07% of Java online submissions for Substring with Concatenation of All Words.
 *
 */
public class xSubstringwithConcatenationofAllWords
{
	public static List<Integer> findSubstring(String s, String[] words) {
		ArrayList<Integer> res = new ArrayList<>();
		if (s==null || words==null || words.length==0)
			return res;
		int len = words[0].length(), n=s.length();
		int window = len*words.length;
		HashMap<String, Integer> dict = new HashMap<>();
		for (String word : words) {
			dict.put(word, dict.getOrDefault(word, 0)+1);
		}
		ArrayList<String> checked = new ArrayList<>(words.length);
		for (int i=0; i<=n-window; ++i) {
			for (int j=0; j<window; j+=len) {
				String key = s.substring(i+j, i+j+len);
				Integer cnt = dict.get(key);
				//System.out.println(key + ":" + cnt);
				if (cnt==null || cnt==0) {
					resetDict(dict, checked);
					break;
				}
				dict.put(key, cnt-1);
				checked.add(key);
			}
			if (checked.size() == words.length) {
				res.add(i);
				resetDict(dict, checked);
			}
		}
		return res;
	}

	private static void resetDict(HashMap<String, Integer> dict, ArrayList<String> checked) {
		for (String key : checked) {
			dict.put(key, dict.get(key)+1);
		}
		checked.clear();
	}

	public static List<Integer> findSubstring2(String s, String[] words) {
		ArrayList<Integer> res = new ArrayList<>();
		if (s==null || s.length()==0 || words==null || words.length==0) return res;
		int len = words[0].length(), n = s.length();
		if (len*words.length>n) return res;
		for (int i=0; i<len; ++i) {
			int lo=i, hi = lo+len*words.length;
			if (hi > n) break;
			HashMap<String, Integer> map = new HashMap<>();
			for (String w : words)
				map.put(w, map.getOrDefault(w, 0)+1);
			int cnt=map.size();
			for (int j=lo; j<hi; j+=len) {
				String w = s.substring(j, j+len);
				Integer c = map.get(w);
				if (c!=null) {
					if (c == 1) cnt--;
					map.put(w, c - 1);
				}
			}
			if (cnt==0) res.add(lo);
			for (lo+=len, hi+=len; hi<=n; lo+=len, hi+=len) {
				String w = s.substring(hi-len, hi);
				Integer c = map.get(w);
				if (c!=null) {
					if (c == 1) cnt--;
					map.put(w, c - 1);
				}
				w = s.substring(lo-len, lo);
				c = map.get(w);
				if (c!=null) {
					if (c == 0) cnt++;
					map.put(w, c + 1);
				}
				if (cnt==0)
					res.add(lo);
			}
		}
		return res;
	}
	/**
	 * Use slide window to cache probable valid solution
	 * time complexity O(n)
	 * space complexity O(m)
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
    public static List<Integer> findSubstring1(String s, String[] words) {
		LinkedList<Integer> res = new LinkedList<Integer>();
        if (words == null || words.length == 0 || s.length() == 0) return res;
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
		System.out.println(findSubstring("wordgoodgoodgoodbestwordword", new String[]{"word","good","best","good"}));
		System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
		System.out.println(findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo","barr","wing","ding","wing"})); //[13]
		System.out.println(findSubstring("aaaaaa", new String[]{"aaa","aaa"}));
	}

}

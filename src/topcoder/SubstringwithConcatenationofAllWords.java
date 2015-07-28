/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class SubstringwithConcatenationofAllWords
{
    public static List<Integer> findSubstring(String s, String[] words) {
        if (words == null || words.length == 0 || s.length() == 0) return null;
        int wordlen = words[0].length();
        HashMap<String, Integer> map = new HashMap<String, Integer>(words.length*4/3+1);
        for (int i=0; i<words.length; ++i) {
            map.put(words[i], i);
        }
        int[] occur = new int[words.length];
        int[] next = new int[words.length];
        int last_index = -1;
        for (int i=0; i<occur.length; ++i) {
            occur[i] = -1;
        }
        int last = s.length() - wordlen;
        LinkedList<Integer> res = new LinkedList<Integer>();
        int cur_begin = -2;
        int i = 0;
        boolean in = false;;
        while (i <= last) {
            String str = s.substring(i, i+wordlen);
            Integer index;
            if ((index = map.get(str)) != null) {
                if (last_index >= 0) {
                    next[last_index] = i;
                }
                if (occur[index] < cur_begin) { // new word
                    occur[index] = i;
                    if (cur_begin<-1) { //not init
                        cur_begin = i;
                    }
                } else { // word occurs
                    cur_begin = next[index];
                    occur[index] = i;
                }
                if (in) {
                    System.out.println((i+wordlen - cur_begin)+":"+words.length*wordlen);
                    if (i + wordlen - cur_begin == words.length*wordlen) { // new result
                        res.add(cur_begin);
                        cur_begin = next[index];
                    }
                } else {
                    in = true;
                }
                i += wordlen;

                last_index = index;
            } else {
                in = false;
                ++i;
                cur_begin = i;
            }
        }
        return res;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{-2,1};
		System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
	}

}

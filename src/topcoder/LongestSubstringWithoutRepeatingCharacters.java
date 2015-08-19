/**
 * 
 */
package topcoder;

/**
 * Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.

Hide Tags Hash Table Two Pointers String
Hide Similar Problems (H) Longest Substring with At Most Two Distinct Characters

从左往右扫描，当遇到重复字母时，以上一个重复字母的index+1，作为新的搜索起始位置，
直到最后一个字母，复杂度是O(n)

 * @author Chauncey
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
    	final int slen = s.length();
    	int[] ci = new int[256]; //ASCII NUM
    	for (int i=0; i<ci.length; ++i) {
    		ci[i] = -1;
    	}
    	int max = 0;
    	int begin = 0;
    	int end = 0;
        while (end<slen) {
        	char ch = s.charAt(end);
        	if (ci[ch] >= begin) { //occur in substring
        		begin = ci[ch] + 1;
        	} else {
        		int len = end-begin+1;
        		if (len>max) {
        			max = len;
        		}
        	}
        	ci[ch] = end;
    		++end;
        }
        return max;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(lengthOfLongestSubstring("bbbbb"));
	}

}

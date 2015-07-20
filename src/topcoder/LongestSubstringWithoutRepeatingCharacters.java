/**
 * 
 */
package topcoder;

/**
 * @author cherry
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

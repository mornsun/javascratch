package topcoder;

/**
 * Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Hide Tags String
Hide Similar Problems (M) Reverse Words in a String II

 * @author Chauncey
 *
 */
public class ReverseWordsinaString {

	/**
	 * 
	 * @param nums
	 * @param begin inclusive
	 * @param end inclusive
	 */
    private final static void reverse(char[] chs, int begin, int end)
    {
    	//0 1 2 3 4 5
    	int mid = begin + ((end+1-begin)>>1);
    	for (int i=begin; i<mid; ++i) {
    	    int right = end-i+begin;
    		char temp = chs[i];
    		chs[i] = chs[right];
    		chs[right] = temp;
    		//nums[i] ^= nums[end-i+begin];
    		//nums[end-i+begin] ^= nums[i];
    		//nums[i] ^= nums[end-i+begin];
    	}
    }
    public static String reverseWords(String s) {
    	if (s == null || s.length() == 0) return s;
    	int len = s.length();
    	char[] str = new char[len+1];
    	int strlen = 0;
    	boolean is_space = true;
    	for (int i=0; i<len; ++i) {
    		char ch = s.charAt(i);
    		if (ch == ' ') {
    			if (!is_space) {
        			is_space = true;
    				str[strlen++] = ch;
    			}
    		} else {
    			if (is_space)
    				is_space = false;
				str[strlen++] = ch;
    		}
    	}
    	if (strlen>0 && str[strlen-1] == ' ')
    		--strlen;
    	str[strlen] = '\0';
    	int start = 0;
    	is_space = true;
    	for (int i=0; i<=strlen; ++i) {
    		char ch = str[i];
    		if (ch == ' ' || ch == '\0') {
    			if (!is_space) {
        			is_space = true;
    				reverse(str, start, i-1);
    			}
    		} else {
    			if (is_space) {
    				is_space = false;
        			start = i;
    			}
    		}
    	}
    	reverse(str, 0, strlen-1);
        return new String(str, 0, strlen);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("["+reverseWords("the  sky is blue ")+"]");
		System.out.println("["+reverseWords("")+"]");
		System.out.println("["+reverseWords(" ")+"]");
	}

}

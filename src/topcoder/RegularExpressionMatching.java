/**
 * 
 */
package topcoder;

/**
 * @author cherry
 *
 */
public class RegularExpressionMatching {

    public static String longestPalindrome(String s) {
    	if (s==null || "".equals(s)) return "";
    	final int slen = s.length();
        boolean[][] dp = new boolean[slen][slen];
        int max = 1; //max points at the 1st character
        int max_begin = 0;
        int max_end = 0;
        for (int j=0; j<slen; ++j) {
        	for (int i=0; i<=j; ++i) {
        		if (i==j) {
        			dp[i][j] = true;
        		} else if (j==i+1 || dp[i+1][j-1]) {
        			if (s.charAt(i) == s.charAt(j)) {
        				dp[i][j] = true;
        				int l = j-i+1;
        				if (l>max) {
        					max = l;
        					max_begin = i;
        					max_end = j;
        				}
        			} else
        				dp[i][j] = false;
        		} else {
        			dp[i][j] = false;
        		}
        	}
        }
        /*for (int j=0; j<slen; ++j) {
        	for (int i=0; i<j; ++i) {
        		System.out.print(i+":"+j+":"+dp[i][j] +"\t");
        	}
        	System.out.println();
        }
        System.out.println(max);*/
        return s.substring(max_begin, max_end+1);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(longestPalindrome("aaabcdcbcdha"));
	}

}

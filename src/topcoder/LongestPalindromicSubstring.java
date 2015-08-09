/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class LongestPalindromicSubstring {

	private static class BackIndex {
		int si;
		int pi;
		BackIndex(int i, int j) {
			si=i;
			pi=j;
		}
	}
    public static boolean isMatch1(String s, String p) {
    	/*if ("".equals(p)) {
    		return "".equals(s);
    	}*/
    	int slen = s.length();
    	int plen = p.length();
    	int si = 0;
    	int pi = 0;
    	int off = 0;
    	Stack<BackIndex> backstack = new Stack<BackIndex>();
        while (off!=plen) {
            //System.out.println(si+":"+s.substring(si)+" "+pi+":"+p.substring(pi));
        	char sch = si==slen ? 0 : s.charAt(si);
        	char pch = pi==plen ? 0 : p.charAt(pi);
        	if (pch==0) {
        		if (backstack.isEmpty()) {
        			off++;
        			pi = off;
        			si = 0;
        		} else {
	        		BackIndex back = backstack.pop();
	        		si = back.si+1;
	        		pi = back.pi;
        		}
        	} else {
	        	if (pch != '*') {
	        		//System.out.println(":"+si+":"+s.substring(si)+" "+pi+":"+p.substring(pi));
	        		if (pch == sch || (pch == '.' && sch != 0)) {
	        			si++;
	        			pi++;
	        		} else {
	            		if (backstack.isEmpty()) {
	            			off++;
	            			pi = off;
	            			si = 0;
	            		} else {
	            			BackIndex back = backstack.pop();
			        		si = back.si+1;
			        		pi = back.pi;
	            		}
	        		}
	        	} else {
	        		if (sch != 0) {
		        		BackIndex back = new BackIndex(si,pi);
		        		backstack.push(back);
	        		}
	        		pi++;
	        	}
        	}
        	if (si==slen && pi==plen) 
        		return true;
        }
        //System.out.println(si+":"+s.substring(si)+" "+pi+":"+p.substring(pi));
        return false;
    }
    public static boolean isMatch(String s, String p) {
    	if ("".equals(p))
    		return "".equals(s);
    	char pch = p.charAt(0);
    	char pch1 = p.length()==1 ? 0 : p.charAt(1);
    	char sch = s.length()==0 ? 0 : s.charAt(0);
    	if (pch1 != '*') {
    		if (pch == sch || (pch=='.' && sch!=0)) {
    			return isMatch(s.substring(1), p.substring(1));
    		}
    		return false;
    	} else {
    		while (pch == sch || (pch=='.' && sch!=0)) {
    			if (isMatch(s, p.substring(2))) {
    				return true;
    			}
    			s = s.substring(1);
    	    	sch = s.length()==0 ? 0 : s.charAt(0);
    		}
    		return isMatch(s, p.substring(2));
    	}
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isMatch("ab", ".*c"));
	}

}

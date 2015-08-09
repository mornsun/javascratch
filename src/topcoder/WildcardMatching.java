package topcoder;

import java.util.Arrays;

/**
 * @author Chauncey
 *
 */
public class WildcardMatching {

	//recursive
	private static boolean isMatch1(String s, String p) {
		int slen = s.length();
		int plen = p.length();
		if (slen == 0 || plen == 0) {
			if (slen == 0 && plen == 0) return true;
			return false;
		}
        
        char sch = s.charAt(0);
        char pch = p.charAt(0);
        if (pch == sch || pch == '?') {
        	return isMatch1(s.substring(1), p.substring(1));
        } else if (pch == '*') {
        	int idx = 0; //skip continuous *
        	do {
        		if (++idx < plen) pch = p.charAt(idx);
        		else return true;
        	} while (pch == '*');
        	for (int i=0; i<=slen; ++i) {
        		if (isMatch1(s.substring(i), p.substring(idx))) return true;
        	}
        }
    	return false;
    }
	
	//iterative
	private static boolean isMatch(String s, String p) {
		int si = 0, pi = 0;
		int slen = s.length(), plen = p.length();
		boolean star = false;
		int sidx=si, pidx=pi;
		while (sidx<slen) {
			char pch = pidx==plen ? 0 : p.charAt(pidx);
			char ssch = sidx==slen ? 0 : s.charAt(sidx);
			System.out.println(pidx+":"+ssch+"-"+pch);
			switch (pch) {
			case '?':
				++sidx;
				++pidx;
				break;
			case '*':
				star = true;
				si = sidx;
				pi = pidx;
				while (pi < plen && p.charAt(pi) == '*') ++pi; //skip continuous *
				if (pi == plen) return true;
				sidx = si;
				pidx = pi;
				break;
			default:
				char sch = s.charAt(sidx);
				if (sch == pch) {
					++sidx;
					++pidx;
				} else {
					if (!star) return false;
					++si;
					sidx = si;
					pidx = pi;
				}
			}
		}
		while (pidx < plen && p.charAt(pidx) == '*') ++pidx;
		return pidx == plen;
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isMatch("abcdefgdefgdefggggg", "a*d*gg"));
	}

}

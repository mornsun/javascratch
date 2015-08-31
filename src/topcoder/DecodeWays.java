package topcoder;

import java.util.*;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

Hide Tags Dynamic Programming String

 * @author Chauncey
 *
 */
public class DecodeWays
{
	/**
	 * f(n) = f(n-2) if s[n-1,n+1] is a code + f(n-1) if s[n,n+1] is a code
	 * @param s
	 * @return
	 */
    public int numDecodings(String s) {
    	if (s==null || s.length() == 0) return 0;
    	int l = s.length();
    	//f0
    	int one = s.charAt(0)-'0';
    	if (one==0) return 0;
    	int f0 = 1;
    	if (l==1) return f0;
    	//f1
    	int two = one*10;
    	one = s.charAt(1)-'0';
    	int f1 = one==0 ? 0 : 1;
    	two += one;
    	if (two<=26) f1 += 1;
    	//dp
    	for (int i=2; i<l; ++i) {
    		two = one*10;
        	one = s.charAt(i)-'0';
    		int f = one==0 ? 0 : f1;
    		if (two != 0) {
	        	two += one;
	        	if (two>0 && two<=26) f += f0;
    		}
        	f0 = f1;
        	f1 = f;
    	}
    	return f1;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		DecodeWays solution = new DecodeWays();
		
		System.out.println(solution.numDecodings("101"));
	}

}

package topcoder;

import java.util.*;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Dynamic Programming Math
Hide Similar Problems (E) Count Primes (M) Ugly Number II

 * @author Chauncey
 *
 */
public class PerfectSquares
{
	/**
	 * 四平方和定理(Lagrange's Four-Square Theorem)：所有自然数至多只要用四个数的平方和就可以表示。
	 * 参考链接：https://leetcode.com/discuss/56982/o-sqrt-n-in-ruby-and-c
	 * 
	 * @param n
	 * @return
	 */
	public int numSquares(int n) {
		if (n<1) return 0;
	    while (n % 4 == 0)
	        n /= 4;
	    if (n % 8 == 7)
	        return 4;
	    for (int a=0; a*a<=n; ++a) {
	        int b = (int)Math.sqrt(n - a*a);
	        if (a*a + b*b == n)
	            return (a>0?1:0) + (b>0?1:0);
	    }
	    return 3;
	}
	
	public int numSquares2(int n) {
	    int ub = (int)Math.sqrt(n);
	    for (int a=0; a<=ub; ++a) {
	        for (int b=a; b<=ub; ++b) {
	            int c = (int)Math.sqrt(n - a*a - b*b);
	            if (a*a + b*b + c*c == n)
	                return (a>0?1:0) + (b>0?1:0) + (c>0?1:0);
	        }
	    }
	    return 4;
	}
	
	/**
	 * DP
	 * 
	 * @param n
	 * @return
	 */
    public int numSquares1(int n) {
        if (n<1) return 0;
        int[] f = new int[n+1];
        f[0] = 0;
        for (int i=1; i<=n; ++i) {
        	int min = f[i-1];
        	int end = (int)Math.sqrt(i);
        	for (int j=2; j<=end; ++j) {
        		int square = j*j;
        		if (f[i-square] < min) min = f[i-square];
        	}
        	f[i] = min+1;
        }
        return f[n];
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PerfectSquares solution = new PerfectSquares();
		for (int i=0; i<20; ++i) {
			System.out.println(i+":"+solution.numSquares(i));
		}
	}

}

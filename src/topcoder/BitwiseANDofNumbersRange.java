package topcoder;

import java.util.*;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

Credits:
Special thanks to @amrsaqr for adding this problem and creating all test cases.

Hide Tags Bit Manipulation

 *
 */
public class BitwiseANDofNumbersRange
{
    public int rangeBitwiseAnd(int m, int n) {
        if (m > n) { //swap
        	m^=n;
        	n^=m;
        	m^=n;
        }
        int shift = 0;
        while (n-m != 0) {
        	++shift;
        	m >>= 1;
        	n >>= 1;
        }
        return m<<shift;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BitwiseANDofNumbersRange solution = new BitwiseANDofNumbersRange();
		System.out.println(solution.rangeBitwiseAnd(2147483647, 2147483647));
	}

}

package topcoder;

import java.util.*;

/**
 * Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Math
Hide Similar Problems (E) Happy Number (E) Count Primes (M) Ugly Number II

 * @author Chauncey
 *
 */
public class UglyNumber
{
    public boolean isUgly(int num) {
    	if (num == 0) return false;
        if (num == 1) return true;
        if ((num & 1) == 0) return isUgly(num>>1);
        if ((num % 3) == 0) return isUgly(num/3);
        if ((num % 5) == 0) return isUgly(num/5);
        return false;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		UglyNumber solution = new UglyNumber();
		
		System.out.println(solution.isUgly(65536));
	}

}

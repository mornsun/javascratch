package topcoder;

import java.util.*;

/**
 * Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Credits:
Special thanks to @mithmatt and @ts for adding this problem and creating all test cases.

Hide Tags Hash Table Math
Hide Similar Problems (E) Add Digits (E) Ugly Number

 * @author Chauncey
 *
 */
public class HappyNumber
{
	private int happy(int number) {
		int sum = 0;
		while (number!=0) {
			int x = number % 10;
			number /= 10;
			sum += x*x;
		}
		return sum;
	}
    public boolean isHappy(int n) {
        if (n == 1)
            return true;
        else if (n == 4)
            return false;
        else
            return isHappy(happy(n));
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		HappyNumber solution = new HappyNumber();
		
		System.out.println(solution.isHappy(19));
	}

}

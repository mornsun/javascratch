package topcoder;

import java.util.*;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
Hide Tags Math
Hide Similar Problems (E) Factorial Trailing Zeroes

 * @author Chauncey
 *
 */
public class NumberofDigitOne
{
    public int countDigitOne(int n) {
    	if (n<=0) return 0;
        long factor = 1;
        long cnt = 0;
        while (n / factor != 0) {
        	int num = (int)(n/(factor*10));
        	int last = (int)((n/factor) % 10);
        	int remainder = (int)(n%factor);
        	switch (last) {
        	case 0:
        		cnt += num*factor;
        		break;
        	case 1:
        		cnt += num*factor+remainder+1;
        		break;
        	default:
        		cnt += (num+1)*factor;
        	}
        	factor *= 10;
        	//System.out.println(last +":"+cnt);
        }
        return (int)cnt;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		NumberofDigitOne solution = new NumberofDigitOne();
		
		System.out.println(solution.countDigitOne(1410065408));
	}

}

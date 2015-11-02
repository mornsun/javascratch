package topcoder;

import java.util.*;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,

Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:

Because the range might be a large number, the low and high numbers are represented as string.

Solution: Take advantage of combinations

 * @author Chauncey
 *
 */
public class xStrobogrammaticNumberIII
{
	private final char[] stro_map = new char[]{'0','1','#','#','#','#','9','#','8','6'};
	private final char[] greaters = new char[]{4,3,3,3,3,3,2,2,1,0};
	private final char[] lesses = new char[]{0,1,2,2,2,2,2,3,3,4};
	private final boolean[] isStrobogrammatic = new boolean[]{true,true,false,false,false,false,true,false,true,true};

    public int strobogrammaticInRange(String low, String high) {
    	if (null == low || null == high) return 0;
    	int lowlen = low.length();
    	int highlen = high.length();
    	int cnt = strobogrammaticInEqualBitNumberGreaters(low)
    			+ strobogrammaticInEqualBitNumberLesses(high);
    	for (int k=lowlen+1; k<highlen; ++k) {
    		cnt += strobogrammaticNumberInBits(0, k);
    	}
    	return cnt;
    }
    
	private final int strobogrammaticInEqualBitNumberGreaters(String low) {
		int n = low.length();
		int cnt = 0;
		int i = 0;
		for (; i<n/2; ++i) { //3:1 4:2
			int digit = low.charAt(i) - '0';
			cnt += greaters[digit] * strobogrammaticNumberInBits(i+1,n);
			if (!isStrobogrammatic[digit]) break;
		}
		if (i == n/2) { //keep strobogrammatic
			if ((n&1) == 1) { //odd number 0,1,8
				int digit = low.charAt(i) - '0';
				if (digit < 1) cnt += 2;
				else if (digit < 8) cnt += 1;
				if (!isStrobogrammatic[digit]) return cnt;
				else ++i;
			}
			for (; i<n; ++i) {
				int digit = low.charAt(n-i-1) - '0';
				if (stro_map[digit] < low.charAt(i)) {
					break;
				}
			}
			if (i==n) ++cnt;
		}
		System.out.println("greater:"+cnt);
		return cnt;
	}
	
	private final int strobogrammaticInEqualBitNumberLesses(String high) {
		int n = high.length();
		int cnt = 0;
		int i = 0;
		for (; i<n/2; ++i) { //3:1 4:2
			int digit = high.charAt(i) - '0';
			if (i == 0) cnt += (lesses[digit]-1) * strobogrammaticNumberInBits(i+1,n);
			else cnt += lesses[digit] * strobogrammaticNumberInBits(i+1,n);
			if (!isStrobogrammatic[digit]) break;
		}
		if (i == n/2) { //keep strobogrammatic
			if ((n&1) == 1) { //odd number 0,1,8
				int digit = high.charAt(i) - '0';
				if (digit > 8) cnt += 3;
				else if (digit > 1) cnt += 2;
				else if (digit > 0) cnt += 1;
				if (!isStrobogrammatic[digit]) return cnt;
				else ++i;
			}
			for (; i<n; ++i) {
				int digit = high.charAt(n-i-1) - '0';
				if (stro_map[digit] > high.charAt(i)) {
					break;
				}
			}
			if (i==n) ++cnt;
		}
		System.out.println("less:"+cnt);
		return cnt;
	}
    
	private final int strobogrammaticNumberInBits(int k, int n) {
		if (k >= (n+1)/2) return 1; //3:0-1 4:0-1
		int cnt = 1;
		for (; k<n/2; ++k) { //3:1 4:2
			cnt *= k==0 ? 4 : 5;
		}
		if ((n&1) == 1) { //odd number 0,1,8
			cnt *= 3;
		}
		return cnt;
	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xStrobogrammaticNumberIII solution = new xStrobogrammaticNumberIII();
		
		//0 1 8 11 69 88 96
		//101 111 181
		//1001 1111 1691 1881 1961
		System.out.println(solution.strobogrammaticInRange("0", "100"));
	}

}

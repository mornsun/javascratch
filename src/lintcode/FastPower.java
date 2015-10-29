package lintcode;

import java.util.*;

/**
 * Calculate the an % b where a, b and n are all 32bit integers.

Have you met this question in a real interview? Yes
Example
For 231 % 3 = 2

For 1001000 % 1000 = 0

Challenge
O(logn)

Tags Expand 
Divide and Conquer

Related Problems Expand 

 * @author Chauncey
 *
 */
public class FastPower
{
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
    	if (b==0 || b==1) return 0;
    	if (n==0 || a==1) return 1;
    	HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
    	memo.put(1, a % b);
    	return fast_power(memo, a, b, n);
    }
    private final int fast_power(HashMap<Integer, Integer> memo, int a, int b, int n) {
    	Integer ret = memo.get(n);
    	if (null == ret) {
    		ret = memo.get(n-1);
    		if (null != ret) {
    			ret = (int)(((long)ret * (a % b)) % b);
    			memo.put(n, ret);
    		} else {
    			int left = fast_power(memo, a, b, n>>1);
    			int right = fast_power(memo, a, b, n-(n>>1));
        		ret = (int)(((long)left * right) % b);
        		memo.put(n, ret);
    		}
    	}
    	return ret;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FastPower solution = new FastPower();

		//10
		System.out.println(solution.fastPower(2,3,31));
		System.out.println(solution.fastPower(100,1000,1000));
		//5249911
		System.out.println(solution.fastPower(109, 10000007, 1000001));
	}

}

/**
 * 
 */
package topcoder;

/**
 * 	Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 * Example 1:
 *
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 * Note:
 *
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 *
 * Array, Sliding window

 * @author Chauncey
 * Runtime: 2 ms, faster than 100.00%
 */
public class LC_1052_Grumpy_Bookstore_Owner
{
    public int maxSatisfied(int[] customers, int[] grumpy, int X)
    {
        if (customers==null || grumpy==null || customers.length==0 || customers.length!=grumpy.length || X<=0)
            return 0;

        int sum = 0;
        for (int i=0; i<X && i<customers.length; ++i) {
            sum += customers[i];
        }

        for (int i=X; i<customers.length; ++i) {
            if (grumpy[i] == 0)
                sum += customers[i];
        }
        //System.out.println(sum);

        int max = sum;
        for (int i=0; i<customers.length-X; ++i) {
            if (grumpy[i]!=0)
                sum -= customers[i];
            if (grumpy[i+X]!=0)
                sum += customers[i+X];
            if (sum > max)
                max = sum;
        }

        return max;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1052_Grumpy_Bookstore_Owner solution = new LC_1052_Grumpy_Bookstore_Owner();
		System.out.println(solution.maxSatisfied(new int[]{1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

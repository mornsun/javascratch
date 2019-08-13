/**
 *
 */
package topcoder;

import java.util.*;

/**
 * 	You have d dice, and each die has f faces numbered 1, 2, ..., f.
 *
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.
 *
 * Example 1:
 *
 * Input: d = 1, f = 6, target = 3
 * Output: 1
 * Explanation:
 * You throw one die with 6 faces.  There is only one way to get a sum of 3.
 * Example 2:
 *
 * Input: d = 2, f = 6, target = 7
 * Output: 6
 * Explanation:
 * You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
 * 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * Example 3:
 *
 * Input: d = 2, f = 5, target = 10
 * Output: 1
 * Explanation:
 * You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.
 * Example 4:
 *
 * Input: d = 1, f = 2, target = 3
 * Output: 0
 * Explanation:
 * You throw one die with 2 faces.  There is no way to get a sum of 3.
 * Example 5:
 *
 * Input: d = 30, f = 30, target = 500
 * Output: 222616187
 * Explanation:
 * The answer must be returned modulo 10^9 + 7.
 *
 * Constraints:
 *
 * 1 <= d, f <= 30
 * 1 <= target <= 1000

 * @author Chauncey
 * Runtime: 4 ms, faster than 95.92% of Java online submissions for Number of Dice Rolls With Target Sum.
 * Memory Usage: 35.1 MB, less than 100.00% of Java online submissions for Number of Dice Rolls With Target Sum.
 */
class LC_1155_Number_of_Dice_Rolls_With_Target_Sum {
    public int numRollsToTarget(int d, int f, int target)
    {
        int[] dp = new int[target+1];
        for (int i=Math.min(f, target); i>=1; --i) {
            dp[i] = 1;
        }
        int lo = 1;
        int hi = f;
        for (int i=1; i<d; ++i) {
            int[] next = new int[target+1];
            for (int j=Math.min(hi, target); j>=lo; --j) {
                for (int k=Math.min(f, target-j); k>=1; --k) {
                    next[j+k] += dp[j];
                    next[j+k] %= 1000000007;
                }
            }
            hi += f;
            lo++;
            dp = next;
        }
        return dp[target];
    }


    public int numRollsToTarget1(int d, int f, int target)
    {
        if (target<1 || target>f*d)
            return 0;
        if (d==1)
            return 1;

        int res = combination(target-1, d-1) - combination(target-1-f, d-1);
        if (res<0) return res + 1000000007;
        else return res;
    }

    private int combination(int sub, int supe)
    {
        int[] bf = new int[supe];
        for (int i=sub; i>sub-supe; --i) {
            bf[sub-i] = i;
        }
        for (int i=supe; i>0; --i) {
            for (int j=0; j<supe; ++j) {
                if (bf[j]%i==0) bf[j] /= i;
            }
        }
        int res = 1;
        for (int i=0; i<supe; ++i) {
            res = (res * bf[i]) % 1000000007;
        }

        System.out.println(res);
        return res;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        LC_1155_Number_of_Dice_Rolls_With_Target_Sum solution = new LC_1155_Number_of_Dice_Rolls_With_Target_Sum();
        System.out.println(solution.numRollsToTarget(1,6,3)); //1
        System.out.println(solution.numRollsToTarget(2,6,7)); //6
        System.out.println(solution.numRollsToTarget(2,5,10)); //1
        System.out.println(solution.numRollsToTarget(1,2,3)); //0
        System.out.println(solution.numRollsToTarget(30,30,500)); //222616187

        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
    }
}

/*11/4=2...3
14/4=3...2

42/4=10...2
154/4=38...2*/

//1000000007

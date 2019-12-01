/**
 * 
 */
package topcoder;

import java.util.Random;

/**
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
 *
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 *
 * Return the maximum profit you can make.
 *
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * Note:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * @author Chauncey
 */
public class xLC_714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee
{
    public int maxProfit(int[] prices, int fee) {
        int fik0 = 0, fik1 = Integer.MIN_VALUE;
        for (int p : prices) {
            int fik0_old = fik0;
            fik0 = Math.max(fik0, fik1+p);
            fik1 = Math.max(fik1, fik0_old-p-fee);
        }
        return fik0;
    }
        
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        xLC_714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee solution = new xLC_714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee();
        System.out.println(solution.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));

        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
    }

}

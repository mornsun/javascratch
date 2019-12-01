/**
 * 
 */
package topcoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

Hide Tags
Array
Dynamic Programming

Hide Similar Problems
Best Time to Buy and Sell Stock
Best Time to Buy and Sell Stock II
Best Time to Buy and Sell Stock IV
Maximum Sum of 3 Non-Overlapping Subarrays

设状态f(i)，表示区间[0,i](0<=i<=n-1) 的最大利润，状态g(i)，表示区间[i,n-1](0<=i<=n-1) 的最大利润，则最终答案为
max {f(i)+g(i)}; 0<=i<=n-1
允许在一天内买进又卖出，相当于不交易，因为题目的规定是最多两次，而不是一定要两次。
将原数组变成差分数组， 本题也可以看做是最大m 子段和，m = 2， 参考代码：
https://gist.github.com/soulmachine/5906637

 * @author Chauncey
 *
 */
public class xBestTimetoBuyandSellStockIII
{
    public static int maxProfit(int[] prices)
    {
    	if (prices.length < 2) return 0;
    	int n = prices.length;
    	int[] f = new int[n];
    	for (int i = 1, valley = prices[0]; i < n; ++i) {
   			if (valley > prices[i]) valley = prices[i];
   			f[i] = Math.max(f[i - 1], prices[i] - valley);
    	}
    	
    	int[] g = new int[n];
    	for (int i = n - 2, peak = prices[n - 1]; i >= 0; --i) {
    		if (peak < prices[i]) peak = prices[i];
            g[i] = Math.max(g[i+1], peak - prices[i]);
    	}
    	
    	int max_profit = 0;
    	for (int i = 0; i < n; ++i)
    		if (max_profit < f[i] + g[i]) max_profit = f[i] + g[i];
    	return max_profit;
    }
    
    public static void datacheck(int[] nums)
    {
        int k = 10000;
        for (int i=0; i<nums.length; ++i) {
            if (k != nums[i]) {
                if (i != 0) {
                    System.out.println("prev:"+(i-1)+":"+nums[i-1]);
                }
                System.out.println(i+":"+nums[i]);
            }
            k--;
        }
    }
    
    
    public static void largetest(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        try {
            String line = br.readLine();
            line = line.substring(1);
            line = line.substring(0, line.length()-1);
            String[] strs = line.split(",");
            int[] nums = new int[strs.length];
            for (int i=0; i<nums.length; ++i) {
                nums[i] = Integer.parseInt(strs[i]);
            }
            int res = maxProfit(nums);
            System.out.println(res);
            //datacheck(nums);
        } finally {
            br.close();
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        System.out.println(maxProfit(new int[]{3,3,5,0,0,3,1,4})); //6
        System.out.println(maxProfit(new int[]{1,2,3,4,5})); //4
        System.out.println(maxProfit(new int[]{7,6,4,3,1})); //0
        System.out.println(maxProfit(new int[]{9,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0})); //0
        try {
            //largetest("/home/work/testdata");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

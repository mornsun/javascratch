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

Hide Tags Array Dynamic Programming
Hide Similar Problems (M) Best Time to Buy and Sell Stock (M) Best Time to Buy and Sell Stock II (H) Best Time to Buy and Sell Stock IV

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

    	int[] g = new int[n];
    	for (int i = 1, valley = prices[0]; i < n; ++i) {
   			if (valley > prices[i]) valley = prices[i];
   			f[i] = f[i - 1] > prices[i] - valley ? f[i - 1] : prices[i] - valley;
    	}
    	for (int i = n - 2, peak = prices[n - 1]; i >= 0; --i) {
    		if (peak < prices[i]) peak = prices[i];
    		g[i] = g[i] > peak - prices[i] ? g[i] : peak - prices[i];
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
        int[] prices = new int[]{9,8,7,6,5,4,3,2,1,0,0,0,0,0,0,0};
        int res = maxProfit(prices);
        System.out.println(res);
        try {
            largetest("/home/work/testdata");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
/**
 * 
 */
package topcoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

贪心法，低进高出，把所有正的价格差价相加起来。
把原始价格序列变成差分序列，本题也可以做是最大m 子段和，m = 数组长度。

 * @author Chauncey
 *
 */
public class BestTimetoBuyandSellStockII
{
    public static int maxProfit(int[] prices)
    {
    	int sum = 0;
    	for (int i = 1; i < prices.length; i++) {
    		int diff = prices[i] - prices[i - 1];
    		if (diff > 0) sum += diff;
    	}
    	return sum;
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

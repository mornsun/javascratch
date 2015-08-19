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
 * Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Hide Tags Array Dynamic Programming
Hide Similar Problems (M) Maximum Subarray (M) Best Time to Buy and Sell Stock II (H) Best Time to Buy and Sell Stock III (H) Best Time to Buy and Sell Stock IV

 * @author Chauncey
 *
 */
public class BestTimetoBuyandSellStock
{
    public static int maxProfit(int[] prices)
    {
        int min = Integer.MAX_VALUE;
        int max_profit = 0;
        for (int i=0; i<prices.length; ++i) {
            if (prices[i] < min) {
                min = prices[i];
                continue;
            }
            int cur_profit = prices[i] - min;
            if (cur_profit > max_profit) {
                max_profit = cur_profit;
            }
        }
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

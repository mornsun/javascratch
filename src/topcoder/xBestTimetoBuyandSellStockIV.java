package topcoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

Hide Tags Dynamic Programming
Hide Similar Problems (M) Best Time to Buy and Sell Stock (M) Best Time to Buy and Sell Stock II (H) Best Time to Buy and Sell Stock III

 * @author Chauncey
 *
 */
public class xBestTimetoBuyandSellStockIV
{
    public static int maxProfit(int k, int[] prices) {
    	if (prices==null || prices.length==0) return 0;
    	if (k > (prices.length>>1)) return maxProfit(prices);
    	if (k == 0) return 0;
    	int[] global = new int[k+1];
    	int[] local = new int[k+1];
    	for (int i=1; i<prices.length; ++i) {
			int diff = prices[i] - prices[i-1];
			int last_global = 0;//global[maxidx-1];
			int max_trans = (i+1)>>1;
    		if (max_trans > k) max_trans = k;
    		for (int j=1; j<=max_trans; ++j) {
    			local[j] = Math.max(last_global+(diff>0?diff:0), local[j]+diff);
    			last_global = global[j];
    			if (local[j] > global[j]) global[j] = local[j];
    		}
    	}
    	return global[k];
    }
    public static int maxProfit(int[] prices)
    {
    	int sum = 0;
    	for (int i = 1; i < prices.length; i++) {
    		int diff = prices[i] - prices[i - 1];
    		if (diff > 0) sum += diff;
    	}
    	return sum;
    }
    public static int maxProfit1(int k, int[] prices) {
    	if (prices==null || prices.length==0 || k==0) return 0;
    	int[][] global = new int[prices.length][k+1];
    	int[][] local = new int[prices.length][k+1];
    	for (int i=1; i<prices.length; ++i) {
			int diff = prices[i] - prices[i-1];
    		local[i][0] = 0;//local[i-1][0] + diff;
			global[i][0] = 0;//Math.max(global[i-1][0], local[i][0]);
    		for (int j=1; j<=k; ++j) {
    			local[i][j] = Math.max(global[i-1][j-1]+(diff>0?diff:0), local[i-1][j]+diff);
    			global[i][j] = Math.max(global[i-1][j], local[i][j]);
    			System.out.print(local[i][j]+":"+global[i][j]+":"+global[i-1][j-1]+":"+i+", ");
    		}
    	}
    	System.out.println();
    	return global[prices.length-1][k];
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        //System.out.println(maxProfit(20, new int[]{9,10,8,10}));
        //System.out.println(maxProfit1(20, new int[]{9,10,8,10}));
        System.out.println(maxProfit(2, new int[]{2,1,2,0,1}));
        System.out.println(maxProfit1(2, new int[]{2,1,2,0,1}));
        System.out.println(maxProfit(2, new int[]{3,2,6,5,0,3}));
        System.out.println(maxProfit1(2, new int[]{3,2,6,5,0,3}));
        System.out.println(maxProfit(2, new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(maxProfit1(2, new int[]{3,3,5,0,0,3,1,4}));
        System.out.println(maxProfit(2, new int[]{1,2,4,2,5,7,2,4,9,0}));
        System.out.println(maxProfit1(2, new int[]{1,2,4,2,5,7,2,4,9,0}));
        System.out.println(maxProfit(1, new int[]{1,2}));
        System.out.println(maxProfit(7, new int[]{48,12,60,93,97,42,25,64,17,56,85,93,9,48,52,42,58,85,81,84,69,36,1,54,23,15,72,15,11,94}));//469

    }

}

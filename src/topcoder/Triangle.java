/**
 * 
 */
package topcoder;

import java.util.*;

import topcoder.MergeTwoSortedLists.ListNode;

/**
 * @author Chauncey
 *
 */
public class Triangle
{
    public static int minimumTotal(List<List<Integer>> triangle) {
    	if (triangle == null) return 0;
    	int max_height = triangle.size();
    	if (max_height == 0) return 0;
    	int max_width = triangle.get(max_height-1).size();
    	int dp[] = new int[max_width];
    	List<Integer> cur = triangle.get(max_height-1);
    	for (int i=0; i<dp.length; ++i) {
    		dp[i] = cur.get(i);
    	}
    	if (max_height >= 2) {
    		for (int i=max_height-2; i>=0; --i) {
    			cur = triangle.get(i);
    			int width = cur.size();
    			for (int j=0; j<width; ++j) {
    				dp[j] = cur.get(j) + ((dp[j] < dp[j+1]) ? dp[j] : dp[j+1]);
    			}
    		}
    	}
    	return dp[0];
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(1);
		list.add(l);
		l = new ArrayList<Integer>();
		l.add(2);l.add(3);
		list.add(l);
		/*l = new ArrayList<Integer>();
		l.add(6);l.add(5);l.add(7);
		list.add(l);
		l = new ArrayList<Integer>();
		l.add(4);l.add(1);l.add(8);l.add(3);
		list.add(l);*/
		System.out.println(minimumTotal(list));
	}

}

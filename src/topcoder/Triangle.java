/**
 * 
 */
package topcoder;

import java.util.*;

import topcoder.MergeTwoSortedLists.ListNode;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

Hide Tags Array Dynamic Programming

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

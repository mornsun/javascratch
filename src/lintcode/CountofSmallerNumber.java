package lintcode;

import java.util.*;

import topcoder.BinarySearchTreeIterator.TreeNode;

/**
 * Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. For each query, give you an integer, return the number of element in the array that are smaller that the given integer.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]

Note
We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

Challenge
Could you use three ways to do it.

Just loop
Sort and binary search
Build Segment Tree and Search.
Tags Expand 
Binary Search LintCode Copyright Segment Tree


Related Problems Expand 
Hard Count of Smaller Number before itself 16 %

 * @author Chauncey
 *
 */
public class CountofSmallerNumber
{
   /**
     * @param A: An integer array
     * @return: The number of element in the array that 
     *          are smaller that the given integer
     */
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	if (null == A || null == queries || queries.length == 0) return res;
    	int[] as = Arrays.copyOf(A, A.length);
    	Arrays.sort(as);
    	for (int query : queries) {
    		int lo = 0;
    		int hi = as.length-1;
    		while (lo<hi) {
    			int m = lo+(hi-lo>>1);
    			if (as[m] < query) {
    				lo = m+1;
    			} else {
    				hi = m;
    			}
    		}
    		res.add(lo);
    	}
    	return res;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CountofSmallerNumber solution = new CountofSmallerNumber();

		System.out.println(solution.countOfSmallerNumber(new int[]{1,2,7,8,5}, new int[]{1,8,5}));
		System.out.println(solution.countOfSmallerNumber(new int[]{}, new int[]{86,59,39}));
		
	}

}

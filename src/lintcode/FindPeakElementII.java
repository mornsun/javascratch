package lintcode;

import java.util.*;

import topcoder.BinarySearchTreeIterator.TreeNode;
import topcoder.LinkedListCycle.ListNode;

/**
 * There is an integer matrix which has the following features:

The numbers in adjacent positions are different.
The matrix has n rows and m columns.
For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
We define a position P is a peek if:

A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
Find a peak element in this matrix. Return the index of the peak.

Have you met this question in a real interview? Yes
Example
Given a matrix:

[
  [1 ,2 ,3 ,6 ,5],
  [16,41,23,22,6],
  [15,17,24,21,7],
  [14,18,19,20,10],
  [13,14,11,10,9]
]
return index of 41 (which is [1,1]) or index of 24 (which is [2,2])

Note
The matrix may contains multiple peeks, find any of them.

Challenge
Solve it in O(n+m) time.

If you come up with an algorithm that you thought it is O(n log m) or O(m log n), can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?

Tags Expand 
Binary Search LintCode Copyright Matrix


Related Problems Expand 
Medium Find Peak Element 44 %

 * @author Chauncey
 *
 */
public class FindPeakElementII
{
    /**
     * time complexity O(m+n)
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
    	List<Integer> res = new ArrayList<Integer>(2);
    	if (A==null || A.length<3 || A[0].length<3) return res;
    	int left = 1, right = A[0].length-2;
    	int top = 1, bottom = A.length-2;
    	while (left <= right && top <= bottom) {
    		int col_mid = left + (right-left>>1);
    		int row_mid = top + (bottom-top>>1);
    		int max = A[row_mid][col_mid];
    		boolean up_more=false, left_more=false;
    		for (int j=left; j<=right; ++j) {
    			if (is_peek(A, row_mid, j)) {
    				res.add(row_mid); res.add(j);
    				return res;
    			}
    			if (A[row_mid][j] > max) {
    	    		max = A[row_mid][j];
    	    		if (A[row_mid-1][j] > A[row_mid+1][j]) {
    	    			up_more = true;
    	    		} else {
    	    			up_more = false;
    	    		}
    	    		left_more = j<col_mid;
    			}
    		}
    		for (int i=top; i<=bottom; ++i) {
    			if (is_peek(A, i, col_mid)) {
    				res.add(i); res.add(col_mid);
    				return res;
    			}
    			if (A[i][col_mid] > max) {
    	    		max = A[i][col_mid];
    	    		if (A[i][col_mid-1] > A[i][col_mid+1]) {
    	    			left_more = true;
    	    		} else {
    	    			left_more = false;
    	    		}
    	    		up_more = i<row_mid;
    			}
    		}
    		//System.out.println(left+"->"+right+" "+top+"->"+bottom+" "+row_mid+":"+col_mid+":"+max);
    		if (up_more) {
    			bottom = row_mid - 1;
    		} else {
    			top = row_mid + 1;
    		}
    		if (left_more) {
    			right = col_mid - 1;
    		} else {
    			left = col_mid + 1;
    		}
    	}
    	return res;
    }
    	
    private final boolean is_peek(int[][] A, int j, int i) {
    	return (A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FindPeakElementII solution = new FindPeakElementII();

		//[1,1] or [2,2]
		int[][] matrix = new int[][]{
				  {1 ,2 ,3 ,6 ,5},
				  {16,41,23,22,6},
				  {15,17,24,21,7},
				  {14,18,19,20,10},
				  {13,14,11,10,9}};
		System.out.println(solution.findPeakII(matrix));
		matrix = new int[][]{
				{1,2,3,4,5,6},
				{14,15,16,17,18,8},
				{12,13,11,10,9,7}};
		System.out.println(solution.findPeakII(matrix));
	}

}

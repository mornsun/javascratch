package lintcode;

import java.util.*;

/**
 * Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?

Have you met this question in a real interview? Yes
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Note
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Challenge
O(n x m) memory is acceptable, can you do it in O(m) memory?

Tags Expand 
LintCode Copyright Dynamic Programming Backpack


Related Problems Expand 
Medium Backpack 19 %

 * @author Chauncey
 *
 */
public class xBackpackII
{
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
    	if (null == A || A.length == 0 || null==V || V.length!=A.length || m<=0) return 0;
    	int[] f = new int[m+1];
    	for (int j=0; j<=m; ++j) {
    		f[j] = j>=A[0] ? V[0] : 0;
    	}
    	for (int i=1; i<A.length; ++i) {
    		f[0] = 0;
    		for (int j=m; j>=A[i]; --j) {
    			f[j] = Math.max(f[j], f[j-A[i]]+V[i]);
    		}
    	}
    	return f[m];
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xBackpackII solution = new xBackpackII();

		//9
		System.out.println(solution.backPackII(10,new int[]{2, 3, 5, 7},new int[]{1,5,2,4}));
		//2558
		System.out.println(solution.backPackII(1000,
				new int[]{3,68,24,80,76,9,24,2,46,75,56,41,95,46,23,34,64,76,6,48,25,73,87,67,58,7,93,66,55,75,38,27,53,6,100,36,26,17,53,88,21,9,34,90,32,47,4,6,57,50,30,25,41,24,12,74,92,17,32,96,35,76,52,93,64,55,1,70,26,35,2,97,82,22,41,37,63,28,90,13,18,55,28,58,59,74,71,32,71,66,4,5,48,52,70,62,28,36,39,48},
				new int[]{38,16,29,47,22,25,17,49,15,15,75,11,56,99,51,92,59,37,13,98,61,50,32,17,44,79,41,53,45,29,62,64,2,23,31,45,57,68,57,26,51,26,86,83,94,20,98,24,91,89,1,63,21,46,74,56,64,72,58,8,74,24,27,35,94,49,65,21,16,25,1,45,63,4,37,25,39,68,49,11,31,95,5,79,20,21,52,50,8,19,67,21,24,89,28,88,38,96,64,84}));
	}

}

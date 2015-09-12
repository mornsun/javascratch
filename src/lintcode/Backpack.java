package lintcode;

import java.util.*;

/**
 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

Have you met this question in a real interview? Yes
Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Note
You can not divide any item into small pieces.

Challenge
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.

Tags Expand 
LintCode Copyright Dynamic Programming Backpack


Related Problems Expand 
Medium Backpack II 33 %

 * @author Chauncey
 *
 */
public class Backpack
{
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
    	if (null == A || A.length == 0 || m<=0) return 0;
    	int[] f = new int[m+1];
    	for (int j=0; j<=m; ++j) {
    		f[j] = j>=A[0] ? A[0] : 0;
    	}
    	for (int i=1; i<A.length; ++i) {
    		f[0] = 0;
    		for (int j=m; j>=A[i]; --j) {
    			f[j] = Math.max(f[j], f[j-A[i]]+A[i]);
    		}
    	}
    	return f[m];
    }
    
    public int backPack1(int m, int[] A) {
    	if (null == A || A.length == 0 || m<=0) return 0;
    	int[][] f = new int[A.length][m+1];
    	for (int j=0; j<=m; ++j) {
    		f[0][j] = j>=A[0] ? A[0] : 0;
    	}
    	for (int i=1; i<A.length; ++i) {
    		f[i][0] = 0;
    		for (int j=0; j<=m; ++j) {
    			if (j<A[i]) f[i][j] = f[i-1][j];
    			else f[i][j] = Math.max(f[i-1][j], f[i-1][j-A[i]]+A[i]);
    		}
    	}
    	return f[A.length-1][m];
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Backpack solution = new Backpack();

		//10
		System.out.println(solution.backPack(11,new int[]{2, 3, 5, 7}));
		//12
		System.out.println(solution.backPack(12,new int[]{2, 3, 5, 7}));
		//9
		System.out.println(solution.backPack(10,new int[]{3,4,8,5}));
	}

}

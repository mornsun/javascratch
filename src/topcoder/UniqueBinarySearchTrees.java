package topcoder;

import java.util.*;

import topcoder.UniqueBinarySearchTreesII.TreeNode;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
Hide Tags Tree Dynamic Programming
Hide Similar Problems (M) Unique Binary Search Trees II

 * @author Chauncey
 *
 */
public class UniqueBinarySearchTrees
{

    public int numTrees(int n) {
        if (n==0) return 0;
        int[] f = new int[n+1];
        f[0] = 1;
        f[1] = 1;
        for (int i=2; i<=n; ++i) {
        	int last = i>>1;
        	for (int j=0; j<last; ++j) {
        		f[i] += f[j] * f[i-1-j];
        	}
        	f[i] <<= 1;
        	if ((i&1)==1) {
        		f[i] += f[last] * f[last];
        	}
        }
        return f[n];
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		UniqueBinarySearchTrees solution = new UniqueBinarySearchTrees();
		
		System.out.println(solution.numTrees(3));
	}

}

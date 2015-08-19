/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.

Hide Tags Backtracking Math
Hide Similar Problems (M) Next Permutation (M) Permutations

 * @author Chauncey
 *
 */
public class PermutationSequence
{
    public static int permutation_num(int n, int r)
    {
        int res = 1;
        for (int i=n-r+1; i<=n; ++i) {
            res *= i;
        }
        return res;
    }
    
    public static String getPermutation(int n, int k) {
        if (k>permutation_num(n,n)) {
            return null;
        }
        k--;
        int[] res = new int[n];
        ArrayList<Integer> vac = new ArrayList<Integer>(n);
        for (int i=1; i<=n; ++i) {
            vac.add(i);
        }
        for (int i=n-1; i>=0; --i) {
            int pn = permutation_num(i,i);
            int b = k/pn;
            k = k%pn;
            res[n-i-1] = vac.get(b);
            vac.remove(b);
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<res.length; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{-2,0,0,2,2};
		System.out.println(getPermutation(3,7));
	}

}

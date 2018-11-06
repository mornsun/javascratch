/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

 Return any permutation of A that maximizes its advantage with respect to B.

 Example 1:

 Input: A = [2,7,11,15], B = [1,10,4,11]
 Output: [2,11,7,15]
 Example 2:

 Input: A = [12,24,8,32], B = [13,25,32,11]
 Output: [24,32,8,12]


 Note:

 1 <= A.length = B.length <= 10000
 0 <= A[i] <= 10^9
 0 <= B[i] <= 10^9


 * @author Chauncey
 * beat 23.71%
 */
public class LC_870_Advantage_Shuffle
{
    public int[] advantageCount(int[] A, int[] B) {
        if (A==null || B==null || A.length != B.length) {
            return null;
        }
        if (A.length == 0) {
            return new int[0];
        }

        int[][] a_idx = new int[A.length][2];
        for (int i=0; i<A.length; ++i) {
            a_idx[i][0] = A[i];
            a_idx[i][1] = i;
        }
        int[][] b_idx = new int[B.length][2];
        for (int i=0; i<B.length; ++i) {
            b_idx[i][0] = B[i];
            b_idx[i][1] = i;
        }
        Arrays.sort(b_idx, (a,b) -> b[0]-a[0]);
        Arrays.sort(a_idx, (a,b) -> b[0]-a[0]);

        int[] ans = new int[A.length];
        int lo=0;
        int hi=A.length-1;
        for (int i=0; i<B.length; ++i) {
            if (a_idx[lo][0] > b_idx[i][0]) {
                ans[b_idx[i][1]] = a_idx[lo][0];
                ++lo;
            } else {
                ans[b_idx[i][1]] = a_idx[hi--][0];
            }
        }
        return ans;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_870_Advantage_Shuffle solution = new LC_870_Advantage_Shuffle();
		System.out.println(Arrays.toString(solution.advantageCount(new int[]{2,7,11,15}, new int[]{1,10,4,11}))); //[2,11,7,15]
        System.out.println(Arrays.toString(solution.advantageCount(new int[]{12,24,8,32}, new int[]{13,25,32,11}))); //[24,32,8,12]
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

/**
 * 
 */
package topcoder;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 *
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 *
 * If it cannot be done, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 * Example 2:
 *
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 *
 *
 * Note:
 *
 * 1 <= A[i], B[i] <= 6
 * 2 <= A.length == B.length <= 20000
 *
 * Array, Greedy
 *
 * @author Chauncey
 * Runtime: 7 ms, faster than 28.74% of Java online submissions for Minimum Domino Rotations For Equal Row.
 * Memory Usage: 42.8 MB, less than 100.00% of Java online submissions for Minimum Domino Rotations For Equal Row.
 */
public class LC_1007_Minimum_Domino_Rotations_For_Equal_Row
{
    public int minDominoRotations(int[] A, int[] B) {
        if (A.length != B.length) { return -1; }
        int[] countA = new int[7]; // countA[i] records the occurrence of i in A.
        int[] countB = new int[7]; // countB[i] records the occurrence of i in B.
        int[] same = new int[7]; // same[k] records the occurrence of k, where k == A[i] == B[i].
        for (int i = 0; i < A.length; ++i) {
            ++countA[A[i]];
            ++countB[B[i]];
            if (A[i] == B[i]) { ++same[A[i]]; }
        }
        for (int i = 1; i < 7; ++i) {
            if (countA[i] + countB[i] - same[i] == A.length) {
                return Math.min(countA[i], countB[i]) - same[i];
            }
        }
        return -1;
    }

    public int minDominoRotations1(int[] A, int[] B) {
        if (A==null || A.length==0 || B==null || B.length==0) return 0;
        int a=A[0], b=B[0], acnt=1, bcnt=1, n=A.length;
        for (int i=1; i<n; ++i) {
            if (A[i]==a || B[i]==a)
                acnt++;
            if (A[i]==b || B[i]==b)
                bcnt++;
        }
        if (acnt!=n && bcnt!=n) return -1;
        a = acnt==n ? a : b;
        acnt=0;
        for (int t : A) {
            if (t==a)
                acnt++;
        }
        bcnt=0;
        for (int t : B) {
            if (t==a)
                bcnt++;
        }

        return Math.min(Math.min(acnt, n-acnt), Math.min(bcnt, n-bcnt));
    }

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1007_Minimum_Domino_Rotations_For_Equal_Row solution = new LC_1007_Minimum_Domino_Rotations_For_Equal_Row();
		System.out.println(solution.minDominoRotations(new int[]{2,1,2,4,2,2}, new int[]{5,2,6,2,3,2}));
        System.out.println(solution.minDominoRotations(new int[]{3,5,1,2,3}, new int[]{3,6,3,3,4}));
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

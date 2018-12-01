/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 	Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
 Return the least number of moves to make every value in A unique.

 Example 1:
 Input: [1,2,2]
 Output: 1
 Explanation:  After 1 move, the array could be [1, 2, 3].

 Example 2:
 Input: [3,2,1,2,1,7]
 Output: 6
 Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

 Note:
 0 <= A.length <= 40000
 0 <= A[i] < 40000

 Related Topics:
 Array

 * @author Chauncey
 * beat %72.42 with 0; %12.43 with 1
 */
public class LC_945_Minimum_Increment_to_Make_Array_Unique
{
	public int minIncrementForUnique(int[] A) {
		if (A==null || A.length==0)
			return 0;

		Arrays.sort(A);
		int taken = 0;
		int ans = 0;
		for (int i=1; i<A.length; ++i) {
			if (A[i] == A[i-1]) {
				++taken;
				ans -= A[i];
			} else if (A[i] > A[i-1]+1 && taken>0) {
				for (int a=A[i-1]+1; a<A[i] && taken>0; ++a) {
					--taken;
					ans += a;
				}
			}
		}
		for (int a=A[A.length-1]+1; taken>0; ++a) {
			--taken;
			ans += a;
		}
		return ans;
	}

	public int minIncrementForUnique1(int[] A) {
		if (A==null || A.length==0)
			return 0;

		int ret = 0;
		TreeSet<int[]> set = new TreeSet<>((a,b)->(a[0]==b[0]?(a[1]-b[1]):(a[0]-b[0])));
		for (int a : A) {
			int moveto = addInt2TreeSet(set, a);
			while (moveto>0) {
				ret += moveto - a;
				moveto = addInt2TreeSet(set, moveto);
			}
		}
		return ret;
	}

	private int addInt2TreeSet(TreeSet<int[]> set, int a) {
		int[] to = new int[]{a,a};
		int[] ceiling = set.ceiling(to);
		int[] floor = set.floor(to);
		if (ceiling != null) {
			if (ceiling[0] == a) {
				return ceiling[1]+1;
			} else if (ceiling[0] == a+1) {
				to[1] = ceiling[1];
				set.remove(ceiling);
			}
		}
		if (floor != null) {
			if (floor[1] >= a) {
				return floor[1]+1;
			} else if (floor[1] == a-1) {
				to[0] = floor[0];
				set.remove(floor);
			}
		}
		set.add(to);
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_945_Minimum_Increment_to_Make_Array_Unique solution = new LC_945_Minimum_Increment_to_Make_Array_Unique();
		System.out.println(solution.minIncrementForUnique(new int[]{1,2,2})); //1
		System.out.println(solution.minIncrementForUnique(new int[]{3,2,1,2,1,7})); //6

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

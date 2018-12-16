/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 	We are given an array A of N lowercase letter strings, all of the same length.

 Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

 For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].

 Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).

 Return the minimum possible value of D.length.


 Example 1:

 Input: ["ca","bb","ac"]
 Output: 1
 Explanation:
 After deleting the first column, A = ["a", "b", "c"].
 Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
 We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.

 Example 2:

 Input: ["xc","yb","za"]
 Output: 0
 Explanation:
 A is already in lexicographic order, so we don't need to delete anything.
 Note that the rows of A are not necessarily in lexicographic order:
 ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)

 Example 3:

 Input: ["zyx","wvu","tsr"]
 Output: 3
 Explanation:
 We have to delete every column.

 Note:

 1 <= A.length <= 100
 1 <= A[i].length <= 100

 Related Topics
 Greedy

 * @author Chauncey
 * beat 87.35%
 */
public class LC_955_Delete_Columns_to_Make_Sorted_II
{
	public int minDeletionSize(String[] A) {
		if (A == null || A.length < 2)
			return 0;

		int cnt = 0;
		boolean[] prev = new boolean[100];
		for (int j = 0; j < 100; ++j) {
			boolean[] curr = new boolean[100];
			boolean less = false;
			for (int i = 1; i<A.length; ++i) {
				if (prev[i]) {
					curr[i] = true;
					continue;
				}
				if (j>=A[i-1].length()) {
					curr[i] = true;
					continue;
				}
				if (j>=A[i].length()) {
					less = true;
					break;
				}
				if (A[i].charAt(j) > A[i-1].charAt(j)) {
					curr[i] = true;
					continue;
				}
				if (A[i].charAt(j) < A[i-1].charAt(j)) {
					less = true;
					break;
				}
			}
			if (less) {
				cnt++;
				continue;
			}
			prev = curr;
		}

		return cnt;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_955_Delete_Columns_to_Make_Sorted_II solution = new LC_955_Delete_Columns_to_Make_Sorted_II();
		System.out.println(solution.minDeletionSize(new String[]{"ca","bb","ac"})); //1
		System.out.println(solution.minDeletionSize(new String[]{"xc","yb","za"})); //0
		System.out.println(solution.minDeletionSize(new String[]{"zyx","wvu","tsr"})); //3
		System.out.println(solution.minDeletionSize(new String[]{"doeeqiy","yabhbqe","twckqte"})); //3

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

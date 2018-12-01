/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 Note that the row index starts from 0.

 In Pascal's triangle, each number is the sum of the two numbers directly above it.
 Example:
 Input: 3
 Output: [1,3,3,1]
 Follow up:
 Could you optimize your algorithm to use only O(k) extra space?

 Related Topics:
 Array

 Similar Questions
 Pascal's Triangle

 * @author Chauncey
 * beat  91.78% 40.22%
 */
public class LC_119_Pascals_Triangle_II
{
	public List<Integer> getRow(int rowIndex) {
		ArrayList<Integer> ret = new ArrayList<>(rowIndex+1);
		if (rowIndex<0)
			return ret;

		int n = rowIndex;
		ret.add(1);
		if (n==0)
			return ret;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i=1; i<=n/2; ++i) {
			long nom = n;
			for (int j=2; j<=i; ++j) {
				if (nom % j == 0)
					nom /= j;
				else
					list.addLast(j);
				while (!list.isEmpty() && (nom % list.peekFirst()) == 0) {
					nom /= list.pollFirst();
				}
				nom *= (n-j+1);
			}
			while (!list.isEmpty() && (nom % list.peekFirst()) == 0) {
				nom /= list.pollFirst();
			}
			ret.add((int)nom);
		}
		for (int i=ret.size()-((n&1)==0?2:1); i>=0; --i) {
			ret.add(ret.get(i));
		}

		return ret;
	}

	public List<Integer> getRow1(int rowIndex) {
		ArrayList<Integer> ret = new ArrayList<>(rowIndex+1);
		if (rowIndex<0)
			return ret;

		ret.add(1);
		for (int i=1; i<=rowIndex; ++i) {
			ret.add(1);
			for (int j=ret.size()-2; j>0; --j) {
				ret.set(j, ret.get(j-1)+ret.get(j));
			}
		}

		return ret;
	}

	public List<List<Integer>> generate(int numRows) {
		ArrayList<List<Integer>> ret = new ArrayList<>(numRows);
		if (numRows<=0)
			return ret;

		ArrayList<Integer> curr = new ArrayList<Integer>(1);
		curr.add(1);
		ret.add(curr);
		for (int i=2; i<=numRows; ++i) {
			ArrayList<Integer> next = new ArrayList<Integer>(i);
			next.add(1);
			for (int j=1; j<i-1; ++j)
				next.add(curr.get(j-1)+curr.get(j));
			next.add(1);

			curr = next;
			ret.add(curr);
		}

		return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_119_Pascals_Triangle_II solution = new LC_119_Pascals_Triangle_II();
		System.out.println(solution.getRow(0)); //[1]
		System.out.println(solution.getRow(1)); //[1,1]
		System.out.println(solution.getRow(2)); //[1,2,1]
		System.out.println(solution.getRow(3)); //[1,3,3,1]
		System.out.println(solution.getRow(18)); //[1,18,153,816,3060,8568,18564,31824,43758,48620,43758,31824,18564,8568,3060,816,153,18,1]
		System.out.println(solution.getRow(30)); // [1,30,435,4060,27405,142506,593775,2035800,5852925,14307150,30045015,54627300,86493225,119759850,145422675,155117520,145422675,119759850,86493225,54627300,30045015,14307150,5852925,2035800,593775,142506,27405,4060,435,30,1]
		System.out.println(solution.getRow(31)); // [1,31,465,4495,31465,169911,736281,2629575,7888725,20160075,44352165,84672315,141120525,206253075,265182525,300540195,300540195,265182525,206253075,141120525,84672315,44352165,20160075,7888725,2629575,736281,169911,31465,4495,465,31,1]
		System.out.println(solution.getRow(32)); // [1,32,496,4960,35960,201376,906192,3365856,10518300,28048800,64512240,129024480,225792840,347373600,471435600,565722720,601080390,565722720,471435600,347373600,225792840,129024480,64512240,28048800,10518300,3365856,906192,201376,35960,4960,496,32,1]

		System.out.println(solution.generate(5));
		System.out.println(solution.generate(0)); //[]

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

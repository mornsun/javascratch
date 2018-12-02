/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 	Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer from [0, N) which is NOT in B.
 Optimize it such that it minimizes the call to system’s Math.random().

 Note:
 1 <= N <= 1000000000
 0 <= B.length < min(100000, N)
 [0, N) does NOT include N. See interval notation.

 Example 1:
 Input:
 ["Solution","pick","pick","pick"]
 [[1,[]],[],[],[]]
 Output: [null,0,0,0]

 Example 2:
 Input:
 ["Solution","pick","pick","pick"]
 [[2,[]],[],[],[]]
 Output: [null,1,1,1]

 Example 3:
 Input:
 ["Solution","pick","pick","pick"]
 [[3,[1]],[],[],[]]
 Output: [null,0,0,2]

 Example 4:
 Input:
 ["Solution","pick","pick","pick"]
 [[4,[2]],[],[],[]]
 Output: [null,1,3,1]

 Explanation of Input Syntax:
 The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments, N and the blacklist B. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.

 Related Topics:
 Hash Table, Binary Search, Sort, Random

 Similar Questions:
 Random Pick Index, Random Pick with Weight

 * @author Chauncey
 * beat 16.24%
 */
public class LC_710_Random_Pick_with_Blacklist
{
	private int[] _blacklist;
	int _n;

	public LC_710_Random_Pick_with_Blacklist(int N, int[] blacklist) {
		_n = N;
		_blacklist = blacklist;
		Arrays.sort(_blacklist);
		for(int i=0; i<_blacklist.length; ++i) {
			_blacklist[i] -= i;
		}
	}

	public int pick() {
		int rand = (int)(Math.random()*(_n - _blacklist.length));
		int ofs = binarySearch(_blacklist, rand);
		System.out.println(_n + "~"+Arrays.toString(_blacklist)+"~"+rand+"~"+ofs);
		return rand+ofs;
	}

	private static int binarySearch(int[] arr, int target) {
		if (arr==null || arr.length==0)
			return 0;
		if (target<arr[0])
			return 0;
		if (target>=arr[arr.length-1])
			return arr.length;

		int lo = 0;
		int hi = arr.length-1;
		while (lo<hi) {
			int m = lo+(hi-lo>>1);
			if (arr[m]<=target) {
				lo = m + 1;
			} else {
				hi = m;
			}
		}
		return lo;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_710_Random_Pick_with_Blacklist solution;
		solution = new LC_710_Random_Pick_with_Blacklist(1, new int[]{});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0
		solution = new LC_710_Random_Pick_with_Blacklist(2, new int[]{});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0
		solution = new LC_710_Random_Pick_with_Blacklist(3, new int[]{1});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0
		solution = new LC_710_Random_Pick_with_Blacklist(4, new int[]{2});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0
		solution = new LC_710_Random_Pick_with_Blacklist(4, new int[]{0,1});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0
		solution = new LC_710_Random_Pick_with_Blacklist(4, new int[]{0,2});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0
		solution = new LC_710_Random_Pick_with_Blacklist(3, new int[]{0});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0
		solution = new LC_710_Random_Pick_with_Blacklist(4, new int[]{0,3});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0
		solution = new LC_710_Random_Pick_with_Blacklist(4, new int[]{2,1});
		System.out.println(solution.pick()+":"+solution.pick()+":"+solution.pick()); //0

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

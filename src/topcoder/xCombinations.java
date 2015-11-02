/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Hide Tags Backtracking
Hide Similar Problems (M) Combination Sum (M) Permutations

生成{1,2,...,n}的字典序r-组合的算法
从r-组合a1a2...ar = 12...r开始
当a1a2...ar != (n-r+1)(n-r+2)...n时，做
1. 确定最大的整数k，使 ak + 1 <= n 且 ak + 1 不是a1,a2,...,ar
2. 用r组合 a1,...,a(k-1),(ak+1),(ak+2),...,(ak+r-k+1) 替换

 * @author Chauncey
 *
 */
public class xCombinations
{
	private final static boolean nextCombination(Integer[] nums, int n) {
		/*for (int e: nums) {
			System.out.print(e);
		}
		System.out.println();*/
		int[] index = new int[n+1];
		int max = 1;
		for (int k=0; k<nums.length; ++k) {
			index[nums[k]] = k+1;
			if (nums[k] > max) max = nums[k];
		}
		int i = max < n-1 ? max : n-1;
		for (; i>0; --i) {
			if(index[i] != 0 && index[i+1] == 0)
				break;
		}
		if (i==0) {
			return false;
		}
		int num = ++nums[index[i]-1];
		for (int k=index[i]; k<nums.length; ++k) {
			nums[k] = ++num;
		}
		return true;
	}
    public static List<List<Integer>> combine(int n, int k) {
    	if (k<1 || n<k) return null;
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Integer[] comb = new Integer[k];
        for (int i=0; i<k; ++i) {
        	comb[i] = i+1;
        }
        List<Integer> one_res = new ArrayList<Integer>(Arrays.asList(comb));//Arrays.asList(comb);
        res.add(one_res);
        while (nextCombination(comb, n)) {
        	one_res = new ArrayList<Integer>(Arrays.asList(comb));
            res.add(one_res);
        }
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List<List<Integer>> list = combine(5,3);
		System.out.println(list);
		
	}

}

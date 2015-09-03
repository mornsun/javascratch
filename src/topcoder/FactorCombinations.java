package topcoder;

import java.util.*;

/**
 * Numbers can be regarded as product of its factors. For example,
8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:
Each combination’s factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
You may assume that n is always positive.
Factors should be greater than 1 and less than n.

Examples:
input: 1
output:
[]
input: 37
output:
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

 * @author Chauncey
 *
 */
public class FactorCombinations
{
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (n <= 0) return res;
		// get all factors
		LinkedList<Integer> path = new LinkedList<Integer>();
		dfs_combination(res, path, n, 2);
		return res;
	}
	private final void dfs_combination(List<List<Integer>> res, LinkedList<Integer> path, int n, int prev) {
		int max = (int)Math.sqrt(n);
		//System.out.println(n+":"+prev+":"+max);
		for (int i=prev; i<=max; ++i) {
			if (n % i == 0) {
				path.add(i);
				dfs_combination(res, path, n/i, i);
				path.removeLast();
			}
		}
		if (n>=prev && !path.isEmpty()) {
			path.add(n);
			res.add(new ArrayList<Integer>(path));
			path.removeLast();
		}
	}
	public List<List<Integer>> getFactors1(int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (n <= 0) return res;
		// get all factors
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for (int i=2; n>1; ) {
			if (n % i == 0) {
				n = n/i;
				factors.add(i);
			} else {
				++i;
			}
		}
		// cache all products
		int sz = factors.size();
		int[][] products = new int[sz][sz];
		for (int i=0; i<sz; ++i) {
			products[i][i] = factors.get(i);
			for (int j=i+1; j<sz; ++j) {
				products[i][j] = products[i][j-1] * factors.get(j);
			}
		}
		// recursively combine answers
		LinkedList<Integer> path = new LinkedList<Integer>();
		for (int i=1; i<sz; ++i) {
			dfs_combination1(res, path, products, 0, i);
		}
		return res;
    }
	private final void dfs_combination1(List<List<Integer>> res, LinkedList<Integer> path,
			int[][] products, int offset, int ngroup) {
		int prev = path.isEmpty() ? 1 : path.getLast();
		if (ngroup == 0) {
			if (products[offset][products.length-1] < prev) return;
			path.add(products[offset][products.length-1]);
			res.add(new ArrayList<Integer>(path));
			path.removeLast();
			return;
		}
		for (int i=offset; i<products.length-ngroup; ++i) {
			if (products[offset][i] < prev) continue;
			path.add(products[offset][i]);
			dfs_combination1(res, path, products, i+1, ngroup-1);
			path.removeLast();
		}
	}
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List<List<Integer>> vec2d = new LinkedList<List<Integer>>();
		List<Integer> vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{1,2}));
		vec2d.add(vec);
		vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{3}));
		vec2d.add(vec);
		vec = new LinkedList<Integer>(Arrays.asList(new Integer[]{4,5,6}));
		vec2d.add(vec);
		FactorCombinations solution = new FactorCombinations();

		System.out.println(solution.getFactors(1));
		System.out.println(solution.getFactors(37));
		System.out.println(solution.getFactors(12));
		System.out.println(solution.getFactors(32));
		System.out.println(solution.getFactors(120));
		System.out.println(solution.getFactors(144));
	}

}

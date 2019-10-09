/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 *
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 *
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 *
 * @author Chauncey
 * Runtime: 46 ms, faster than 85.74% of Java online submissions for Smallest String With Swaps.
 * Memory Usage: 95.5 MB, less than 100.00% of Java online submissions for Smallest String With Swaps.
 */
public class xLC_1202_Smallest_String_With_Swaps
{
	class UnionSet {
		private int count;
		private int[] id;
		private int[] size;

		public UnionSet(int N) {
			this.count = N;
			this.id = new int[N];
			this.size = new int[N];

			for (int i = 0; i < this.count; i++) {
				id[i] = i;
				size[i] = 1;
			}
		}

		private int find(int p) {
			while (p != id[p]) {
				id[p] = id[id[p]];  // 路径压缩，会破坏掉当前节点的父节点的尺寸信息，因为压缩后，当前节点的父节点已经变了
				p = id[p];
			}

			return p;
		}

		public void union(int p, int q) {
			int pCom = this.find(p);
			int qCom = this.find(q);

			if (pCom == qCom) {
				return;
			}
			// 按秩进行合并
			if (size[pCom] > size[qCom]) {
				id[qCom] = pCom;
				size[pCom] += size[qCom];
			} else {
				id[pCom] = qCom;
				size[qCom] += size[pCom];
			}
			// 每次合并之后，树的数量减1
			count--;
		}

		public int count() {
			return this.count;
		}

		public int size(int p) {
			return this.size[find(p)];
		}
	}

	public String smallestStringWithSwaps(String s, int[][] pairs)
	{
		if (s==null || s.length()==0)
			return "";

		int n=s.length();
		UnionSet uset = new UnionSet(n);
		for (int[] pair : pairs) {
			uset.union(pair[0], pair[1]);
		}

		HashMap<Integer, List<Character>> map = new HashMap<>();
		int[] parts = new int[n];
		for (int i=0; i<n; ++i) {
			int p = uset.find(i);
			parts[i] = p;
			List<Character> list = map.get(p);
			if (list==null) {
				list = new ArrayList<Character>();
				map.put(p, list);
			}
			list.add(s.charAt(i));
		}
		for (List<Character> list : map.values()) {
			Collections.sort(list);
			Collections.reverse(list);
		}
		char[] chs = new char[n];
		int top=-1;
		for (int i=0; i<n; ++i) {
			int p = parts[i];
			List<Character> list = map.get(p);
			char last = list.get(list.size()-1);
			list.remove(list.size()-1);
			chs[++top] = last;
		}
		return new String(chs);
	}

	public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs)
	{
		if (s==null || s.length()==0)
			return "";

		int n=s.length();
		UnionSet uset = new UnionSet(n);
		for (List<Integer> pair : pairs) {
			uset.union(pair.get(0), pair.get(1));
		}

		HashMap<Integer, List<Character>> map = new HashMap<>();
		int[] parts = new int[n];
		for (int i=0; i<n; ++i) {
			int p = uset.find(i);
			parts[i] = p;
			List<Character> list = map.get(p);
			if (list==null) {
				list = new ArrayList<Character>();
				map.put(p, list);
			}
			list.add(s.charAt(i));
		}
		for (List<Character> list : map.values()) {
			Collections.sort(list);
			Collections.reverse(list);
		}
		char[] chs = new char[n];
		int top=-1;
		for (int i=0; i<n; ++i) {
			int p = parts[i];
			List<Character> list = map.get(p);
			char last = list.get(list.size()-1);
			list.remove(list.size()-1);
			chs[++top] = last;
		}
		return new String(chs);
	}

	public String smallestStringWithSwaps1(String s, int[][] pairs)
	{
		if (s==null || s.length()==0)
			return "";

		int n=s.length();
		UnionSet uset = new UnionSet(n);
		for (int[] pair : pairs) {
			uset.union(pair[0], pair[1]);
		}

		int[] prev = new int[n];
		int[] prvs = new int[n];
		for (int i=0; i<n; ++i) {
			int p = uset.find(i);
			prev[i] = prvs[p] - 1;
			prvs[p] = i + 1;
		}
		char[] chs = s.toCharArray();
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i=0; i<n; ++i) {
				if (prev[i] == -1)
					continue;
				if (chs[i] >= chs[prev[i]])
					continue;
				char tmp = chs[i];
				chs[i] = chs[prev[i]];
				chs[prev[i]] = tmp;
				flag = true;
			}
		}
		return new String(chs);
	}

		/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1202_Smallest_String_With_Swaps solution = new xLC_1202_Smallest_String_With_Swaps();
        System.out.println(solution.smallestStringWithSwaps("dcab", new int[][]{{0,3},{1,2}})); //"bacd"
		System.out.println(solution.smallestStringWithSwaps("dcab", new int[][]{{0,3},{1,2},{0,2}})); //"abcd"
		System.out.println(solution.smallestStringWithSwaps("cba", new int[][]{{0,1},{1,2}})); //"abc"
		System.out.println(solution.smallestStringWithSwaps("dcab", new int[][]{})); //dcab
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

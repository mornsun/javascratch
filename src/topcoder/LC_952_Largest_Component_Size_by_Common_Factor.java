/**
 * 
 */
package topcoder;

import java.util.*;
/**
 * 	Given a non-empty array of unique positive integers A, consider the following graph:

 There are A.length nodes, labelled A[0] to A[A.length - 1];
 There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
 Return the size of the largest connected component in the graph.

 Example 1:

 Input: [4,6,15,35]
 Output: 4

 Example 2:

 Input: [20,50,9,63]
 Output: 2

 Example 3:

 Input: [2,3,6,7,4,12,21,39]
 Output: 8

 Note:

 1 <= A.length <= 20000
 1 <= A[i] <= 100000

 Related Topics
 Math, Union Find

 * @author Chauncey
 * Runtime: 165 ms, faster than 79.23%
 */
public class LC_952_Largest_Component_Size_by_Common_Factor
{
	public int largestComponentSize(int[] A) {
		if (A==null || A.length==0)
			return 0;

		ArrayList<Integer>[] factors = new ArrayList[A.length];
		for (int i=0; i<A.length; ++i) {
			factors[i] = new ArrayList<>();
			int d=2, x=A[i];
			while(d*d <= x) {
				if (x % d == 0) {
					while (x % d == 0) {
						x /= d;
					}
					factors[i].add(d);
				}
				++d;
			}

			if (x>1)
				factors[i].add(x);
		}

		HashMap<Integer, Integer> hmap = new HashMap<>();
		initUnionSet(A.length);
		for (int i=0; i<factors.length; ++i) {
			for (int fct : factors[i]) {
				Integer id = hmap.get(fct);
				if (id == null) {
					hmap.put(fct, i);
				} else {
					union(i, id);
				}
			}
		}

		int max = 1;
		for (int i=0; i<A.length; ++i) {
			if (size[i] > max)
				max = size[i];
		}

		return max;
	}

	private int count;
	private int[] id;
	private int[] size;

	public void initUnionSet(int N) {
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

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_952_Largest_Component_Size_by_Common_Factor solution = new LC_952_Largest_Component_Size_by_Common_Factor();
		System.out.println(solution.largestComponentSize(new int[]{4,6,15,35})); //4
		System.out.println(solution.largestComponentSize(new int[]{20,50,9,63})); //2
		System.out.println(solution.largestComponentSize(new int[]{2,3,6,7,4,12,21,39})); //8


		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

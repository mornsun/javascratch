/**
 * 
 */
package topcoder;

import java.util.HashMap;

/**
 * 	On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

 Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

 What is the largest possible number of moves we can make?

 Example 1:

 Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 Output: 5

 Example 2:

 Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 Output: 3

 Example 3:

 Input: stones = [[0,0]]
 Output: 0

 Note:

 1 <= stones.length <= 1000
 0 <= stones[i][j] < 10000

 Related Topics
 Depth-first search, Union Find

 * @author Chauncey
 * Runtime: 21 ms, faster than 76.38%
 */
public class LC_947_Most_Stones_Removed_with_Same_Row_or_Column
{
	public int removeStones(int[][] stones) {
		if (stones==null || stones.length<=1)
			return 0;

		initUnionSet(stones.length);

		HashMap<Integer, Integer> xmap = new HashMap<>(stones.length);
		HashMap<Integer, Integer> ymap = new HashMap<>(stones.length);

		xmap.put(stones[0][0], 0);
		ymap.put(stones[0][1], 0);

		for (int i=1; i<stones.length; ++i) {
			int[] stone = stones[i];
			Integer id = xmap.get(stone[0]);
			if (id != null) {
				union(i, id);
			} else {
				xmap.put(stone[0], i);
			}
			id = ymap.get(stone[1]);
			if (id != null) {
				union(i, id);
			} else {
				ymap.put(stone[1], i);
			}
		}
		return stones.length - count;
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

		LC_947_Most_Stones_Removed_with_Same_Row_or_Column solution = new LC_947_Most_Stones_Removed_with_Same_Row_or_Column();
		System.out.println(solution.removeStones(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}})); //5
		System.out.println(solution.removeStones(new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}})); //3
		System.out.println(solution.removeStones(new int[][]{{0,0}})); //0

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

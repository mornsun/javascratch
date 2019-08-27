/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
 *
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 *
 * Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 *
 * Example 1:
 *
 * Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * Output: [0,1,-1]
 * Example 2:
 *
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * Output: [0,1,-1]
 * Example 3:
 *
 * Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * Output: [0,-1,-1]
 * Example 4:
 *
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * Output: [0,1,2]
 * Example 5:
 *
 * Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * Output: [0,1,1]
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 *
 * BFS, Graph

 * @author Chauncey
 * Runtime: 3 ms, faster than 99.24% of Java online submissions for Shortest Path with Alternating Colors.
 * Memory Usage: 44.4 MB, less than 100.00% of Java online submissions for Shortest Path with Alternating Colors.
 */
public class LC_1129_Shortest_Path_with_Alternating_Colors
{
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        if (n<=0)
            return null;

        int[] r_dist = new int[n];
        int[] b_dist = new int[n];
        HashMap<Integer, ArrayList<Integer>> r_map = this.initGraph(red_edges);
        HashMap<Integer, ArrayList<Integer>> b_map = this.initGraph(blue_edges);

        ArrayList<int[]> curr = new ArrayList<>();
        this.initQueue(r_map, curr, r_dist, -1);
        this.initQueue(b_map, curr, b_dist, 1);
        int dist = 2;
        while(!curr.isEmpty()) {
            ArrayList<int[]> next = new ArrayList<>();
            for (int[] u : curr) {
                int c = u[1]==1 ? -1 : 1;
                HashMap<Integer, ArrayList<Integer>> m = c==1 ? b_map : r_map;
                ArrayList<Integer> vs = m.get(u[0]);
                if (vs==null) continue;
                for (int v : vs) {
                    //System.out.println(u[0]+ "|" + v + "|" + c + "|" + res[v]);
                    if (c<0) {
                        if (r_dist[v] != 0) continue;
                        r_dist[v] = dist;
                    } else {
                        if (b_dist[v] != 0) continue;
                        b_dist[v] = dist;
                    }
                    next.add(new int[]{v, c});
                }
            }
            curr = next;
            dist++;
        }
        int[] res = new int[n];
        for (int i=1; i<n; ++i) {
            if (r_dist[i]>0 && b_dist[i]>0)
                res[i] = Math.min(r_dist[i], b_dist[i]);
            else if (r_dist[i]==0  && b_dist[i]==0)
                res[i] = -1;
            else
                res[i] = Math.max(r_dist[i], b_dist[i]);
        }
        res[0] = 0;
        return res;
    }

    private HashMap<Integer, ArrayList<Integer>> initGraph(int[][] edges)
    {
        HashMap<Integer, ArrayList<Integer>> m = new HashMap<>();
        if (edges == null) return m;
        for (int[] edge : edges) {
            ArrayList<Integer> tos = m.get(edge[0]);
            if (tos == null) {
                tos = new ArrayList<>();
                m.put(edge[0], tos);
            }
            tos.add(edge[1]);
        }
        return m;
    }

    private void initQueue(HashMap<Integer, ArrayList<Integer>> m, ArrayList<int[]> curr, int[] dist, int c) {
        ArrayList<Integer> tos = m.get(0);
        if (tos == null) return;
        for (int to : tos) {
            curr.add(new int[]{to, c});
            dist[to] = 1;
        }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1129_Shortest_Path_with_Alternating_Colors solution = new LC_1129_Shortest_Path_with_Alternating_Colors();
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(1, new int[][]{}, new int[][]{}))); //[0]
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0,1},{1,2}}, new int[][]{}))); //[0,1,-1]
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0,1}}, new int[][]{{2,1}}))); //[0,1,-1]
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{1,0}}, new int[][]{{2,1}}))); //[0,-1,-1]
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0,1}}, new int[][]{{1,2}}))); //[0,1,2]
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(3, new int[][]{{0,1},{0,2}}, new int[][]{{1,0}}))); //[0,1,1]
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(5, new int[][]{{0,1},{1,2},{2,3},{3,4}}, new int[][]{{1,2},{2,3},{3,1}}))); //[0,1,2,3,7]
        System.out.println(Arrays.toString(solution.shortestAlternatingPaths(7, new int[][]{{0,5},{3,1},{3,6},{1,0},{1,4},{1,2},{5,6},{5,0},{0,6},{0,3},{5,4},{6,1},{5,5},{1,1},{4,6},{2,2}}, new int[][]{{3,1},{4,3},{5,4},{5,3},{5,1},{3,2},{4,4},{5,0},{1,2},{1,1},{4,5},{0,0}}))); //[0, 2, 2, 1, 2, 1, 1]

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}

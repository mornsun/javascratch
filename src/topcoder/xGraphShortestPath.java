package topcoder;

import java.util.*;

/**
 * Shortest Path
 * 
 */
public class xGraphShortestPath
{
    private final static Comparator<int[]> comparator = new Comparator<int[]>() {
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    };
	/**
	 * Dijkstra algorithm: for non-negative weighted graph (like Prim algorithm)
	 * O((V+E)logV) with heap, O(V2) with array
	 * @param n
	 * @param edges
	 * @param start - start vertex
	 * @param to - to vertex
	 * @return
	 */
    public List<Integer> graphDijkstra(int n, int[][] edges, int start, int to) { 
    	if (n <= 0) return null;
    	if (start < 0 || to < 0 || start >= n || to >= n) return null;
        if (edges == null || edges.length == 0) {
        	return null;
        }
        ArrayList<ArrayList<int[]>> graph = new ArrayList<ArrayList<int[]>>();
        for (int i=0; i<n; ++i)
        	graph.add(new ArrayList<int[]>());
        for (int[] edge : edges) {
        	graph.get(edge[0]).add(edge);
        }

        int[] prev = new int[n];
        int[] dist = new int[n];
        for (int i=0; i<n; ++i)
        	dist[i] = Integer.MAX_VALUE;
        dist[start] = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(n, comparator);
        heap.add(new int[]{0, start});
        while (!heap.isEmpty()) {
        	int[] vertex = heap.poll();
        	int d = vertex[0];
        	int u = vertex[1];
        	if (dist[u] < d) continue; //has updated
        	if (u == to) break; //arrive at so-called to vertex
        	for (int[] edge : graph.get(u)) {
        		int v = edge[1];
        		int w = edge[2];
        		if (dist[u] + w < dist[v]) {
        			dist[v] = dist[u]+w;
        			prev[v] = u;
        			heap.add(new int[]{dist[v], v});
        		}
        	}
        }
        LinkedList<Integer> res = new LinkedList<Integer>();
    	res.addFirst(dist[to]);
        for (int v=to; v!=start; v=prev[v]) {
        	res.addFirst(v);
        }
        res.addFirst(start);
        return res;
    }
    
    /**
     * for acyclic DAG: topological sort and then BFS
     * O(V+E)
     */

	/**
	 * Bellman-Ford algorithm: for a graph with some negative weighted edges
	 * @param n
	 * @param edges
	 * @param start - start vertex
	 * @param to - to vertex
	 * @return
	 */
    public List<Integer> graphBellmanFord(int n, int[][] edges, int start, int to) { 
    	if (n <= 0) return null;
    	if (start < 0 || to < 0 || start >= n || to >= n) return null;
        if (edges == null || edges.length == 0) {
        	return null;
        }

        int[] prev = new int[n];
        int[] dist = new int[n];
        for (int i=0; i<n; ++i)
        	dist[i] = Integer.MAX_VALUE;
        dist[start] = 0;
        for (int i=1; i<n; ++i) {
        	for (int[] edge : edges) {
        		int u = edge[0];
        		if (dist[u] == Integer.MAX_VALUE) continue;
        		int v = edge[1];
        		int w = edge[2];
        		if (dist[u] + w < dist[v]) {
        			dist[v] = dist[u] + w;
        			prev[v] = u;
        		}
        	}
        }
        for (int[] edge : edges) {
        	if (dist[edge[1]] > dist[edge[0]] + edge[2])
        		return null;
        }
        LinkedList<Integer> res = new LinkedList<Integer>();
    	res.addFirst(dist[to]);
        for (int v=to; v!=start; v=prev[v]) {
        	res.addFirst(v);
        }
        res.addFirst(start);
        return res;
    }
    
	/**
	 * Floyd-Warshall algorithm: distance of arbitrary two vertex for a graph with some negative weighted edges
	 * @param n
	 * @param edges
	 * @param start - start vertex
	 * @param to - to vertex
	 * @return
	 */
    public List<Integer> graphFloydWarshall(int n, int[][] edges, int start, int to) { 
    	if (n <= 0) return null;
    	if (start < 0 || to < 0 || start >= n || to >= n) return null;
        if (edges == null || edges.length == 0) {
        	return null;
        }
        int[][] prev = new int[n][n];
        int[][] dist = new int[n][n];
        for (int i=0; i<n; ++i) {
        	for (int j=0; j<n; ++j) {
        		prev[i][j] = -1;
        		dist[i][j] = Integer.MAX_VALUE;
        	}
        	dist[i][i] = 0;
        }
    	for (int[] edge : edges) {
    		dist[edge[0]][edge[1]] = edge[2];
    		prev[edge[0]][edge[1]] = edge[0];
    	}
		for (int k=0; k<n; ++k) {
			//NOTE: 0,...,k-1 must to be evaluated previously to be taken as intermediate vertexes of i->k and k->j
			for (int i=0; i<n; ++i) {
				if (i==k) continue;
				for (int j=0; j<n; ++j) {
					if (i==j) continue;
					if (j==k) continue;
    				if (dist[i][k]==Integer.MAX_VALUE || dist[k][j]==Integer.MAX_VALUE)continue;
    				int d = dist[i][k] + dist[k][j];
    				if (d < dist[i][j]) {
    					dist[i][j] = d;
    					if (prev[k][j]==-1) prev[i][j] = k;
    					else prev[i][j] = prev[k][j];
    				}
    			}
    		}
    	}

        LinkedList<Integer> res = new LinkedList<Integer>();
    	res.addFirst(dist[start][to]);
        for (int v=to; v!=-1; v=prev[start][v]) {
        	res.addFirst(v);
        }
        return res;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//The test cases are extracted from "Introduction of Algorithm"
		xGraphShortestPath solution = new xGraphShortestPath();
		System.out.println(solution.graphDijkstra(5, new int[][]{{0,1,10},{0,3,5},{1,3,2},{1,2,1},{2,4,4},{3,1,3},{3,2,9},{3,4,2},
				{4,0,7},{4,2,6}}, 0, 1));
		System.out.println(solution.graphBellmanFord(5, new int[][]{{0,1,10},{0,3,5},{1,3,2},{1,2,1},{2,4,4},{3,1,3},{3,2,9},{3,4,2},
				{4,0,7},{4,2,6}}, 0, 1));
		System.out.println(solution.graphBellmanFord(5, new int[][]{{0,1,6},{0,3,7},{1,2,5},{1,3,8},{1,4,-4},{2,1,-2},{3,2,-3},{3,4,9},
				{4,0,2},{4,2,7}}, 0, 4));
		System.out.println(solution.graphFloydWarshall(5, new int[][]{{0,1,6},{0,3,7},{1,2,5},{1,3,8},{1,4,-4},{2,1,-2},{3,2,-3},{3,4,9},
				{4,0,2},{4,2,7}}, 0, 4));
		System.out.println(solution.graphFloydWarshall(5, new int[][]{{0,1,3},{0,2,8},{0,4,-4},{1,3,1},{1,4,7},{2,1,4},{3,2,-5},{3,0,2},
				{4,3,6}}, 4, 1));
		System.out.println();
	}

}

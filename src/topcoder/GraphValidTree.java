package topcoder;

import java.util.*;

/**
 * Problem Description:

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together inedges.

 */
public class GraphValidTree
{
    public boolean validTree(int n, int[][] edges) { 
    	if (n <= 0) return false;
        if (edges == null || edges.length == 0) {
        	return n==1;
        }
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<n; ++i) graph.add(new ArrayList<Integer>());
        for (int[] edge : edges) {
        	graph.get(edge[0]).add(edge[1]);
        	graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        if (false == has_cycle(graph, visited, 0, -1))
        	return false;
        for (boolean v : visited) {
        	if (v == false)
        		return false;
        }
        return true;
    }

	private boolean has_cycle(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int vertex, int prev) {
		visited[vertex] = true; //grey
		ArrayList<Integer> edges = graph.get(vertex);
		if (edges != null) {
			for (int next_vertex : edges) {
				if (next_vertex == prev) continue;
				if (visited[next_vertex] == false) { //white
					if (false == has_cycle(graph, visited, next_vertex, vertex))
						return false;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		GraphValidTree solution = new GraphValidTree();
		System.out.println(solution.validTree(11, new int[][]{{1,2},{3,1},{2,3},{2,4},{3,8},{3,9},{4,5},{4,7},
				{5,6},{6,7},{7,5},{8,10},{8,0},{9,8},{0,10},{10,9},{0,6},{8,7}}));
		System.out.println(solution.validTree(5, new int[][]{{0,1},{0,2},{0,3},{1,4}}));
		System.out.println(solution.validTree(5, new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}));
		System.out.println();
	}

}

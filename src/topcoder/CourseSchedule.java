package topcoder;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
Hide Tags Depth-first Search Breadth-first Search Graph Topological Sort
Hide Similar Problems (M) Course Schedule II (M) Graph Valid Tree

 *
 */
public class CourseSchedule
{
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses<=0 || prerequisites==null)
			return true;
		int[] res = new int[numCourses];
		ArrayList<List<Integer>> table = new ArrayList<>(numCourses);
		for (int i=0; i<numCourses; ++i) {
			table.add(new ArrayList<Integer>());
		}
		for (int[] prerequisite : prerequisites) {
			table.get(prerequisite[1]).add(prerequisite[0]);
		}
		int[] visited = new int[numCourses];
		for (int i=0; i<numCourses; ++i) {
			if (helper(res, table, visited, i) == false)
				return false;
		}
		return true;
	}

	private boolean helper(int[] res, ArrayList<List<Integer>> table, int[] visited, int i) {
		if (visited[i]==1) //black
			return true;
		if (visited[i]==-1) //cyclic
			return false;
		visited[i] = -1; //grey
		for (int next : table.get(i)) {
			if (helper(res, table, visited, next) == false)
				return false;
		}
		visited[i] = 1; //black
		return true;
	}

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
    	if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0) return true;
    	int[] vertexs = new int[numCourses];
        HashMap<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
        for (int[] prerequisite : prerequisites) {
        	Set<Integer> edges = graph.get(prerequisite[1]);
        	if (edges == null) {
        		edges = new HashSet<Integer>();
        		edges.add(prerequisite[0]);
        		graph.put(prerequisite[1], edges);
        	} else {
        		edges.add(prerequisite[0]);
        	}
        }
        for (int i=0; i<numCourses; ++i) {
        	if (vertexs[i] == 0) { //white
        		if (graph_dfs(graph, vertexs, i) == false) {
        			return false;
        		}
        	}
        }
        return true;
    }

	private boolean graph_dfs(Map<Integer, Set<Integer>> graph, int[] vertexs, int vertex) {
		vertexs[vertex] = 1; //grey
		Set<Integer> edges = graph.get(vertex);
		if (edges != null) {
			for (Integer next_vertex : graph.get(vertex)) {
				if (vertexs[next_vertex] == 0) { //white
					if (graph_dfs(graph, vertexs, next_vertex) == false) return false; //cyclic
				} else if (vertexs[next_vertex] == 1) //grey, represent cyclic
					return false;
			}
		}
		vertexs[vertex] = -1; //black
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CourseSchedule solution = new CourseSchedule();
		System.out.println(solution.canFinish(4, new int[][]{{1,0},{2,0},{3,1},{3,2}}));
		System.out.println(solution.canFinish(2, new int[][]{{1,0},{0,1}}));
	}

}

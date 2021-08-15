package topcoder;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
Hide Tags Depth-first Search Breadth-first Search Graph Topological Sort
Hide Similar Problems (M) Course Schedule (M) Alien Dictionary

 *
 */
public class xCourseScheduleII
{
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		if (numCourses<=0 || prerequisites==null)
			return new int[0];
		int[] res = new int[numCourses];
		ArrayList<List<Integer>> table = new ArrayList<>(numCourses);
		for (int i=0; i<numCourses; ++i) {
			table.add(new ArrayList<Integer>());
		}
		for (int[] prerequisite : prerequisites) {
			table.get(prerequisite[1]).add(prerequisite[0]);
		}
		int[] visited = new int[numCourses];
		int res_i = numCourses - 1;
		for (int i=0; i<numCourses; ++i) {
			res_i = helper(res, table, visited, i, res_i);
			if (res_i == -10)
				return new int[0];
		}
		return res;
	}

	private int helper(int[] res, ArrayList<List<Integer>> table, int[] visited, int i, int res_i) {
		if (visited[i]==1) //black
			return res_i;
		if (visited[i]==-1) //cyclic
			return -10;
		visited[i] = -1; //grey
		for (int next : table.get(i)) {
			res_i = helper(res, table, visited, next, res_i);
			if (res_i == -10)
				return -10;
		}
		visited[i] = 1; //black
		res[res_i] = i;
		return res_i - 1;
	}

    public int[] findOrder1(int numCourses, int[][] prerequisites) {
    	if (numCourses <= 0) return null;
    	int[] res = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0) {
        	for (int i=0; i<numCourses; ++i) {
        		res[i] = i;
        	}
        	return res;
        }
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
        int current = numCourses-1;
        for (int i=0; i<numCourses; ++i) {
        	if (vertexs[i] == 0) { //white
        		current = graph_dfs(graph, res, current, vertexs, i);
        		if (current == Integer.MIN_VALUE) {
        			return new int[0];
        		}
        	}
        }
        return res;
    }

	private int graph_dfs(Map<Integer, Set<Integer>> graph, int[] res, int current, int[] vertexs, int vertex) {
		vertexs[vertex] = 1; //grey
//    	System.out.println("search:"+vertex+":"+current);
		Set<Integer> edges = graph.get(vertex);
		if (edges != null) {
			for (Integer next_vertex : graph.get(vertex)) {
//				System.out.println(vertex+"->"+next_vertex+":"+current);
//		    	System.out.println(vertexs[next_vertex]);
				if (vertexs[next_vertex] == 0) { //white
					current = graph_dfs(graph, res, current, vertexs, next_vertex);
					if (current == Integer.MIN_VALUE) return Integer.MIN_VALUE; //cyclic
				} else if (vertexs[next_vertex] == 1) //grey, represent cyclic
					return Integer.MIN_VALUE;
			}
		}
		res[current] = vertex;
		vertexs[vertex] = -1; //black
		return current-1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xCourseScheduleII solution = new xCourseScheduleII();
		System.out.println(Arrays.toString(solution.findOrder(2, new int[][]{{1,0}})));
		System.out.println(Arrays.toString(solution.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
		System.out.println(Arrays.toString(solution.findOrder1(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
	}

}

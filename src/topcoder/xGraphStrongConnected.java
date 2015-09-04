package topcoder;

import java.util.*;

/**
 * SCC (Strongly connected components)
 * Kosaraju's Two-Pass Algorithm (2 round DFS)
 * https://class.coursera.org/algo-003/lecture/53
 */
public class xGraphStrongConnected
{
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	if (numCourses <= 0) return null;
    	int[] leaders = new int[numCourses];
    	int[] finishtimes = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0) {
        	for (int i=0; i<numCourses; ++i) {
        		leaders[i] = i;
        	}
        	return leaders;
        }
    	int[] visited = new int[numCourses];
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
        //Kosaraju's Two-Pass Algorithm
        //1st round: evaluate finish times
        int time = 0;
        for (int i=0; i<numCourses; ++i) {
        	if (visited[i] == 0) { //white
        		time = graph_dfs1(graph, visited, i, finishtimes, time);
        	}
        }
        //2nd round: evaluate leader of every SCC (Strongly connected components)
        Arrays.fill(visited, 0);
        for (int i=0; i<numCourses; ++i) {
        	int vertex = finishtimes[i];
        	if (visited[vertex] == 0) { //white
        		graph_dfs2(graph, visited, vertex, leaders, vertex);
        	}
        }
        return leaders;
    }

	private int graph_dfs1(Map<Integer, Set<Integer>> graph, int[] vertexs, int vertex, int[] finishtimes, int time) {
		vertexs[vertex] = 1; //grey
		Set<Integer> edges = graph.get(vertex);
		if (edges != null) {
			for (Integer next_vertex : edges) {
				if (vertexs[next_vertex] == 0) { //white
					time = graph_dfs1(graph, vertexs, next_vertex, finishtimes, time);
				}
			}
		}
		finishtimes[time] = vertex;
		return time+1;
	}
	private void graph_dfs2(Map<Integer, Set<Integer>> graph, int[] visited, int vertex, int[] leaders, int leader) {
		visited[vertex] = 1; //grey
		leaders[vertex] = leader;
		Set<Integer> edges = graph.get(vertex);
		if (edges != null) {
			for (Integer next_vertex : edges) {
				if (visited[next_vertex] == 0) { //white
					graph_dfs2(graph, visited, next_vertex, leaders, leader);
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xGraphStrongConnected solution = new xGraphStrongConnected();
		int[] nums = solution.findOrder(11, new int[][]{{1,2},{3,1},{2,3},{2,4},{3,8},{3,9},{4,5},{4,7},
				{5,6},{6,7},{7,5},{8,10},{8,0},{9,8},{0,10},{10,9},{0,6},{8,7}});
		for (int num : nums)
			System.out.print(num+",");
		System.out.println();
	}

}

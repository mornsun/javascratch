package topcoder;

import java.util.*;

/**
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

 * 
 */
public class FindtheCelebrity
{
	boolean knows(ArrayList<HashSet<Integer>> graph, int a, int b) {
		return graph.get(a).contains(b);
	}
    public int findCelebrity(int n, int[][] edges) {
    	if (n <= 0) return -1;
        if (edges == null || edges.length == 0) {
        	return 0;
        }
        ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>();
        for (int i=0; i<n; ++i)
        	graph.add(new HashSet<Integer>());
        for (int[] edge : edges) {
        	graph.get(edge[0]).add(edge[1]);
        }

        int c = 0;
        for (int i=1; i<n; ++i) {
        	if (!knows(graph, i, c)) {
        		c = i;
        	}
        }
        for (int i=0; i<n; ++i) {
        	if (i == c) continue;
        	if (!knows(graph, i, c) || knows(graph, c, i)) {
        		return -1;
        	}
        }
        return c;
    }
    public int findCelebrity1(int n, int[][] edges) {
    	if (n <= 0) return -1;
        if (edges == null || edges.length == 0) {
        	return 0;
        }
        ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>();
        for (int i=0; i<n; ++i)
        	graph.add(new HashSet<Integer>());
        for (int[] edge : edges) {
        	graph.get(edge[0]).add(edge[1]);
        }
        int c = 0;
        for(int i=1; i<n; ++i){
            if(knows(graph, c, i)){
                c = i;
            }
        }
 
        for(int i=0; i<n; ++i){
            if(c!=i && (knows(graph, c,i) || !knows(graph, i,c))) return -1;
        }
        return c;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//The test cases are extracted from "Introduction of Algorithm"
		FindtheCelebrity solution = new FindtheCelebrity();
		System.out.println(solution.findCelebrity(6, new int[][]{{1,5},{2,5},{3,5},{4,5},{0,5},{1,0},{2,0},{3,0},{4,0}}));
		System.out.println(solution.findCelebrity1(6, new int[][]{{1,5},{2,5},{3,5},{4,5},{0,5},{1,0},{2,0},{3,0},{4,0}}));
	}

}

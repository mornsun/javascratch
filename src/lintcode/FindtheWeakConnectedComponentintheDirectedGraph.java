package lintcode;

import java.util.*;

/**
 * Find the number Weak Connected Component in the directed graph. Each node in the graph contains a label and a list of its neighbors. (a connected set of a directed graph is a subgraph in which any two vertices are connected by direct edge path.)

Have you met this question in a real interview? Yes
Example
Given graph:

A----->B  C
 \     |  | 
  \    |  |
   \   |  |
    \  v  v
     ->D  E <- F
Return {A,B,D}, {C,E,F}. Since there are two connected component which are {A,B,D} and {C,E,F}

Note
Sort the element in the set in increasing order

Tags Expand 
Union Find


Related Problems Expand 
Hard Number of Islands II 11 %
Medium Find the Weak Connected Component in the Directed Graph 17 %

 *
 * @author Chauncey
 *
 */
public class FindtheWeakConnectedComponentintheDirectedGraph
{
	/**
	 * Definition for Directed graph.
	 */
	 class DirectedGraphNode {
	     int label;
	     ArrayList<DirectedGraphNode> neighbors;
	     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
	 };

    /**
     * @param nodes a array of Directed graph node
     * @return a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        // Write your code here
    	return null;
    }
	

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FindtheWeakConnectedComponentintheDirectedGraph solution = new FindtheWeakConnectedComponentintheDirectedGraph();
	}

}

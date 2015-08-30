package topcoder;

import java.util.*;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
Hide Tags Depth-first Search Breadth-first Search Graph
Hide Similar Problems (H) Copy List with Random Pointer

 * @author Chauncey
 *
 */
public class CloneGraph
{
	public static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	};
	private final UndirectedGraphNode dfs_clone_graph(UndirectedGraphNode node, HashMap<Integer,UndirectedGraphNode> visited) {
		UndirectedGraphNode newnode = new UndirectedGraphNode(node.label);
		visited.put(node.label, newnode);
		for (UndirectedGraphNode neighbor : node.neighbors) {
			if (neighbor == null) continue;
			UndirectedGraphNode new_neighbor = visited.get(neighbor.label);
			if (new_neighbor == null) {
				new_neighbor = dfs_clone_graph(neighbor, visited);
			}
			newnode.neighbors.add(new_neighbor);
		}
		return newnode;
	}
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	if (node == null) return null;
        HashMap<Integer,UndirectedGraphNode> visited = new HashMap<Integer,UndirectedGraphNode>();
        return dfs_clone_graph(node, visited);
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CloneGraph solution = new CloneGraph();
		UndirectedGraphNode node1 = new UndirectedGraphNode(1);
		UndirectedGraphNode node2 = new UndirectedGraphNode(2);
		node1.neighbors.add(node2);
		node2.neighbors.add(node1);
		UndirectedGraphNode newnode = solution.cloneGraph(node1);
		System.out.println(node1+":"+node2+":"+newnode.neighbors);
		
	}

}

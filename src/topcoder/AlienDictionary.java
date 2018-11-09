package topcoder;

import java.util.*;

/**
 * There is a new alien language which uses the latin alphabet. However,
the order among letters are】 unknown to you. You receive a list of words from the dictionary,
where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:

You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
Hide Tags Depth-first Search Breadth-first Search Graph Topological Sort
Hide Similar Problems (M) Course Schedule (M) Course Schedule II

 *
 */
public class AlienDictionary
{
    public String alienOrder(String[] words) {
    	if (words == null || words.length==0) return "";
        HashMap<Character, Byte> charset = new HashMap<Character, Byte>();
        // get charset
    	for (String word : words) {
    		int k = word.length();
    		while (k != 0) charset.put(word.charAt(--k), (byte)0);
    	}
    	// build graph
        HashMap<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
    	String prev = words[0];
    	for (int i=1; i<words.length; ++i) {
    		int k = 0;
    		String word = words[i];
    		while (prev.charAt(k) == word.charAt(k)) ++k;
    		if (k==prev.length() || k==word.length()) continue;
    		Set<Character> edges = graph.get(prev.charAt(k));
    		if (edges == null) {
        		edges = new HashSet<Character>();
        		graph.put(prev.charAt(k), edges);
    		}
        	edges.add(word.charAt(k));
        	prev = word;
    	}
    	StringBuilder res = new StringBuilder();
    	for (Map.Entry<Character, Byte> entry : charset.entrySet()) {
    	    byte visited = entry.getValue();
    	    if (visited == 0) { //white
        	    char ch = entry.getKey();
        		if (!graph_dfs(graph, res, charset, ch)) return null;
    	    }
    	}
    	return res.toString();
    }

	private boolean graph_dfs(HashMap<Character, Set<Character>> graph, StringBuilder res, HashMap<Character, Byte> charset, char vertex) {
		charset.put(vertex, (byte)1); //grey
//    	System.out.println("search:"+vertex+":"+current);
		Set<Character> edges = graph.get(vertex);
		if (edges != null) {
			for (char next_vertex : graph.get(vertex)) {
				byte visited = charset.get(next_vertex);
				if (visited == 0) { //white
					if (!graph_dfs(graph, res, charset, next_vertex)) return false; //cyclic
				} else if (visited == 1) //grey, represent cyclic
					return false;
			}
		}
		res.insert(0, vertex);
		charset.put(vertex, (byte)-1); //black
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		AlienDictionary solution = new AlienDictionary();
		System.out.println(solution.alienOrder(new String[]{
				  "wrt",
				  "wrf",
				  "er",
				  "ett",
				  "rftt"}));
		System.out.println(solution.alienOrder(new String[]{"baa", "abcd", "abca", "cab", "cad"}));
		System.out.println(solution.alienOrder(new String[]{"caa", "aaa", "aab"}));
	}

}

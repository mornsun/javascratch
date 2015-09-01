/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
Hide Tags Array Backtracking Breadth-first Search String

 * @author Chauncey
 *
 */
public class WordLadderII
{
	private class TrieNode{
		TrieNode[] next;
		boolean end;
		public TrieNode() {
			next = new TrieNode[26];
			end = false;
		}
	}
	private final TrieNode _root = new TrieNode();
	
    // Adds a word into the data structure.
    public void addWord(String word) {
    	if (null == word) return;
    	int l = word.length();
    	TrieNode node = _root;
        for (int i=0; i<l; ++i) {
        	int ch = word.charAt(i)-'a';
        	TrieNode next_node = node.next[ch];
        	if (next_node == null) {
        		next_node = new TrieNode();
        		node.next[ch] = next_node;
        	}
        	node = next_node;
        }
        node.end = true;
    }

    private void dfs_search(List<String> res, StringBuilder path, char[] word, TrieNode node, int idx) {
    	if (idx == word.length) {
    		if (node.end == true) {
    			res.add(path.toString());
    		}
    		return;
    	}
    	char ch = word[idx];
    	if (ch == '.') {
    		for (int i=0; i<node.next.length; ++i) {
    			TrieNode next_node = node.next[i];
    			if (next_node == null) continue;
    			int tail = path.length();
    			path.append((char)('a'+i));
    			dfs_search(res, path, word, next_node, idx+1);
    			path.delete(tail, path.length());
    		}
    	} else {
        	node = node.next[ch-'a'];
        	if (node == null) return;
			path.append(ch);
        	dfs_search(res, path, word, node, idx+1);
    	}
    }
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public List<String> search(char[] word) {
    	if (null == word) return null;
    	List<String> res = new ArrayList<String>();
    	StringBuilder path = new StringBuilder();
    	dfs_search(res, path, word, _root, 0);
        return res;
    }
    
    private static class LadderTreeNode{
    	String word;
    	LadderTreeNode parent;
    	public LadderTreeNode(String w, LadderTreeNode p) { word = w; parent=p;}
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
    	List<List<String>> res = new ArrayList<List<String>>();
    	if (start==null || end==null) return res;
    	int len = start.length();
    	if (end.length() != len) return res;
    	addWord(start);
    	addWord(end);
    	for (String word : dict) {
    		if (word.length() != len) continue;
    		addWord(word);
    	}
    	HashSet<String> visited = new HashSet<String>();
    	LinkedList<LadderTreeNode> parents = new LinkedList<LadderTreeNode>();
    	LinkedList<LadderTreeNode> children = new LinkedList<LadderTreeNode>();
    	children.offer(new LadderTreeNode(start, null));
    	visited.add(start);
    	boolean arrived = false;
    	while (!children.isEmpty()) {
    		LinkedList<LadderTreeNode> tmp = children;
    		children = parents;
    		parents = tmp;
    		while (!parents.isEmpty()) {
    			LadderTreeNode parent = parents.poll();
    			char[] word = parent.word.toCharArray();
    			for (int i=0; i<len; ++i) {
    				char ch = word[i];
    				word[i] = '.';
        			List<String> nexts = search(word);
        			word[i] = ch;
        			for (String next : nexts) {
        				if (visited.contains(next)) continue;
        				if (end.equals(next)) {
        					LinkedList<String> path = new LinkedList<String>();
        					path.push(next);
        					path.push(parent.word);
        					LadderTreeNode node = parent.parent;
        					while (node != null) {
        						path.push(node.word);
        						node = node.parent;
        					}
        					res.add(path);
        					arrived = true;
        				} else {
	        				LadderTreeNode child = new LadderTreeNode(next, parent);
	        				children.offer(child);
        				}
        			}
    			}
    		}
    		if (arrived) {
    			break;
    		}
    		for (LadderTreeNode child : children) {
    			visited.add(child.word);
    		}
    	}
    	return res;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WordLadderII solution = new WordLadderII();
		String[] words = new String[]{"hot","dot","dog","lot","log"};
		HashSet<String> dict = new HashSet<String>(Arrays.asList(words));
		//System.out.println(solution.findLadders("hit", "cog", dict));
		System.out.println(solution.findLadders("a", "c", new HashSet<String>(Arrays.asList(new String[]{"b"}))));
		
	}

}

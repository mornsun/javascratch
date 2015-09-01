/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

 * @author Chauncey
 *
 */
public class WordLadder
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

    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
    	if (beginWord==null || endWord==null) return 0;
    	int len = beginWord.length();
    	if (endWord.length() != len) return 0;
    	addWord(beginWord);
    	addWord(endWord);
    	for (String word : wordDict) {
    		if (word.length() != len) continue;
    		addWord(word);
    	}
    	int step = 2;
    	HashSet<String> visited = new HashSet<String>();
    	LinkedList<String> parents = new LinkedList<String>();
    	LinkedList<String> children = new LinkedList<String>();
    	children.offer(beginWord);
    	visited.add(beginWord);
    	while (!children.isEmpty()) {
    		LinkedList<String> tmp = children;
    		children = parents;
    		parents = tmp;
    		while (!parents.isEmpty()) {
    			char[] word = parents.poll().toCharArray();
    			for (int i=0; i<len; ++i) {
    				char ch = word[i];
    				word[i] = '.';
        			List<String> nexts = search(word);
        			for (String next : nexts) {
        				if (visited.contains(next)) continue;
        				if (endWord.equals(next)) return step;
        				children.offer(next);
        				visited.add(next);
        			}
        			word[i] = ch;
    			}
    		}
    		++step;
    	}
    	return 0;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WordLadder solution = new WordLadder();
		String[] words = new String[]{"hot","dot","dog","lot","log"};
		HashSet<String> dict = new HashSet<String>(Arrays.asList(words));
		System.out.println(solution.ladderLength("hit", "cog", dict));
		//System.out.println(solution.ladderLength("hot", "dog", new HashSet<String>(Arrays.asList(new String[]{"hot", "dog"}))));
		
	}

}

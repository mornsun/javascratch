/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

click to show hint.

You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
Hide Tags Backtracking Data Structure Trie
Hide Similar Problems (M) Implement Trie (Prefix Tree)

 * @author Chauncey
 *
 */
public class WordDictionary
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

    private boolean dfs_search(String word, TrieNode node, int idx, int l) {
    	if (idx == l) return node.end == true;
    	char ch = word.charAt(idx);
    	if (ch == '.') {
    		for (TrieNode next_node : node.next) {
    			if (next_node == null) continue;
    			if (dfs_search(word, next_node, idx+1, l)) {
    				return true;
    			}
    		}
    		return false;
    	} else {
        	node = node.next[ch-'a'];
        	if (node == null) return false;
        	return dfs_search(word, node, idx+1, l);
    	}
    }
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
    	if (null == word) return false;
    	int l = word.length();
        return dfs_search(word, _root, 0, l);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WordDictionary wordDictionary = new WordDictionary();
		wordDictionary.addWord("word");
		System.out.println(wordDictionary.search("w.rd"));
		
	}

}

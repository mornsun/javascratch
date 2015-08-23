'''
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

Hide Tags Backtracking Trie
Hide Similar Problems (M) Word Search

@author: Chauncey
'''

from collections import deque

class TreeNode:
    def __init__(self, ch):
        self.ch = ch
        self.next = {}
        self.end = False
        
class TrieTree:
    def __init__(self):
        self.root = TreeNode('#')
        
    def addWord(self, word):
        if word is None or len(word)==0: return
        node = self.root
        for ch in word:
            next_node = node.next.get(ch)
            if next_node is None:
                next_node = TreeNode(ch)
                node.next[ch] = next_node
            node = next_node
        node.end = True
    
    def output(self):
        node = self.root
        queue = deque()
        queue.append(node)
        while len(queue) != 0:
            node = queue.popleft()
            print '['+node.ch+':',
            for k,v in node.next.items():
                queue.append(v)
                print k+',',
            if node.end: print '$',
            print ']'

class Solution:
    # @param {character[][]} board
    # @param {string[]} words
    # @return {string[]}
    def findWords(self, board, words):
        if board is None or len(board) == 0 or len(board[0]) == 0: return []
        if words is None or len(words) == 0: return []
        trie = TrieTree()
        for word in words:
            trie.addWord(word)
        #trie.output()
        return self.exist(board, trie.root)
        
    # @param {character[][]} board
    # @param {string} word
    # @return {boolean}
    def exist(self, board, node):
        m = len(board)
        n = len(board[0])
        visited = [[False] * n for i in xrange(m)]
        res = set()
        for i in xrange(m):
            for j in xrange(n):
                self.dfs(res, board, m, n, '', node, i, j, visited)
        return list(res)
    
    def dfs(self, res, board, m, n, word, node, i, j, visited):
        for k,v in node.next.items():
            if k == board[i][j]:
                if v.end:
                    #print i, j, k, board[i][j], word
                    res.add(word+k)
                visited[i][j] = True
                if i>0 and (not visited[i-1][j]):
                    self.dfs(res, board, m, n, word+k, v, i-1, j, visited)
                if j>0 and (not visited[i][j-1]):
                    self.dfs(res, board, m, n, word+k, v, i, j-1, visited)
                if i<m-1 and (not visited[i+1][j]):
                    self.dfs(res, board, m, n, word+k, v, i+1, j, visited)
                if j<n-1 and (not visited[i][j+1]):
                    self.dfs(res, board, m, n, word+k, v, i, j+1, visited)
                visited[i][j] = False
        
                
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.findWords(["oaan","etae","ihkr","iflv"], ["oath","pea","eat","rain", 'rock'])
    print solution.findWords(["a"], ['a'])
    
    
    
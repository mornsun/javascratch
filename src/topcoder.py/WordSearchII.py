'''

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
    
    
    
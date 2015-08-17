'''

@author: Chauncey
'''

class Solution:
    # @param {character[][]} board
    # @param {string[]} words
    # @return {string[]}
    def findWords(self, board, words):
        if board is None or len(board) == 0 or len(board[0]) == 0: return False
        if words is None or len(words) == 0: return False
        res = []
        for word in words:
            if self.exist(board, word):
                res.append(word)
        return res
        
    # @param {character[][]} board
    # @param {string} word
    # @return {boolean}
    def exist(self, board, word):
        m = len(board)
        n = len(board[0])
        visited = [[False] * n for i in xrange(m)]
        for i in xrange(m):
            for j in xrange(n):
                if self.dfs(board, m, n, word, 0, i, j, visited):
                    return True
        return False
    
    def dfs(self, board, m, n, word, k, i, j, visited):
        if word[k] == board[i][j]:
            if k == len(word)-1: return True
            visited[i][j] = True
            if i>0 and (not visited[i-1][j]) and self.dfs(board, m, n, word, k+1, i-1, j, visited):
                return True
            if j>0 and (not visited[i][j-1]) and self.dfs(board, m, n, word, k+1, i, j-1, visited):
                return True
            if i<m-1 and (not visited[i+1][j]) and self.dfs(board, m, n, word, k+1, i+1, j, visited):
                return True
            if j<n-1 and (not visited[i][j+1]) and self.dfs(board, m, n, word, k+1, i, j+1, visited):
                return True
            visited[i][j] = False
        return False
        
                
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.findWords(["oaan","etae","ihkr","iflv"], ["oath","pea","eat","rain"])
    
    
    
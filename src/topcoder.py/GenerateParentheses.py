'''

@author: Chauncey
'''

class Solution:
    # @param {integer} n
    # @return {string[]}
    def generateParenthesis(self, n):
        if n == 0: return ['']
        if n == 1: return ['()']
        res = []
        for i in xrange(n):
            for inner in self.generateParenthesis(i):
                for outer in self.generateParenthesis(n-i-1):
                    res.append('('+inner+')'+outer)
        return res
    
    def generateParenthesis1(self, n):
        res = []
        if n == 0: return res
        path = []
        self.dfs(res, path, n, n)
        return res
    
    def dfs(self, res, path, left, right):
        if left == 0:
            one = ''.join(path) + ')' * right
            res.append(one)
            return
        path.append('(')
        self.dfs(res, path, left-1, right)
        path.pop()
        if right > left:
            path.append(')')
            self.dfs(res, path, left, right-1)
            path.pop()
        return
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.generateParenthesis(3)
    
    
    
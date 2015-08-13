'''

@author: Chauncey
'''
class Solution:
    # @param {string} s
    # @return {integer}
    def lengthOfLastWord(self, s):
        if s is None:
            return 0
        s = s.strip()
        slen = len(s)
        if slen is 0:
            return 0
        idx = s.rfind(' ')
        if idx is -1:
            return slen
        else:
            return slen-idx-1
        
solution = Solution();
print solution.lengthOfLastWord('hello world')
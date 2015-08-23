'''
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.

Hide Tags String

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
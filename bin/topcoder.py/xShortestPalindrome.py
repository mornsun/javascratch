'''
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

Credits:
Special thanks to @ifanchu for adding this problem and creating all test cases. Thanks to @Freezen for additional test cases.

Hide Tags String
Hide Similar Problems (M) Longest Palindromic Substring (E) Implement strStr()

@author: Chauncey
'''

class Solution:
    # @param {string} s
    # @return {string}
    def shortestPalindrome(self, s):
        rev_s = s[::-1]
        l = s + '#' + rev_s
        print l
        p = [0] * len(l)
        for i in range(1, len(l)):
            j = p[i - 1]
            while j > 0 and l[i] != l[j]:
                j = p[j - 1]
            p[i] = j + (l[i] == l[j])
            print i,j,p
        return rev_s[: len(s) - p[-1]] + s
    
    def shortestPalindrome1(self, s):
        if s is None or len(s) == 0: return s
        l = len(s)
        m = (len(s)+1)/2
        max = 1
        for k in xrange(m-1,0,-1):
            #print "test:",k,l,s[:k+1]
            if l >= m<<1 and s[k] == s[k+1]:
                i = k - 1
                j = k + 2
                while i>=0 and s[i] == s[j]:
                    i -= 1
                    j += 1
                if i < 0:
                    max = j
                    break
            i = k - 1
            j = k + 1
            while i>=0 and s[i] == s[j]:
                i -= 1
                j += 1
            if i < 0:
                max = j
                break
        res = s
        if max != 0:
            res = s[max:][::-1]+s
        print len(res)
        return res
                
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.shortestPalindrome('abcb')
    
    
    
'''

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
    print solution.shortestPalindrome('aaaa')
    
    
    
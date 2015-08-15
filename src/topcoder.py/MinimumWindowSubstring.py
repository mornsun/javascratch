'''

@author: Chauncey
'''
import sys

class Solution:
    # @param {string} s
    # @param {string} t
    # @return {string}
    def minWindow(self, s, t):
        if s is None or t is None: return ''
        slen = len(s)
        tlen = len(t)
        if slen == 0 or tlen == 0: return ''
        patterns = [0 for i in xrange(256)]
        hits = [0 for i in xrange(256)]
        nhit = 0
        for ch in t:
            patterns[ord(ch)] += 1
        lo = 0
        hi = 0
        nminwin = sys.maxsize
        lminwin = 0
        rminwin = 0
        while lo <= hi:
            if nhit != tlen:
                if hi >= slen:
                    break
                ch = ord(s[hi])
                if hits[ch] < patterns[ch]:
                    nhit += 1
                hits[ch] += 1
                hi += 1
            else:
                if hi-lo < nminwin:
                    nminwin = hi - lo
                    lminwin = lo
                    rminwin = hi
                if lo >= slen:
                    break
                ch = ord(s[lo])
                hits[ch] -= 1
                if hits[ch] < patterns[ch]:
                    nhit -= 1
                lo += 1
        if nminwin is sys.maxsize: return ''
        return s[lminwin:rminwin]
                
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.minWindow('ADOBECODEBANC', 'ZZZZ')
    
    
    
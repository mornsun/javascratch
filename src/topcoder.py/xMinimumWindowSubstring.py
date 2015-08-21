'''
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

Hide Tags Hash Table Two Pointers String
Hide Similar Problems (H) Substring with Concatenation of All Words (M) Minimum Size Subarray Sum (H) Sliding Window Maximum

@author: Chauncey
'''
import sys

'''
          0123456789
Example: "ababcbcbaaabbdef" 
has a 2-character substring "baaabb" of length 6 (longest) 
and a 2-character substring "bcbcb" of lenght 5 (second longest).
'''


class Solution:
    #Google Round 1
    def maxWindow(self, s, k):
        if s is None or k < 1: return ''
        slen = len(s)
        if slen == 0: return ''
        hits = [0 for i in xrange(256)]
        nhit = 0
        lo = 0
        hi = 0
        maxwin = 0
        lwin = 0
        rwin= 0
        while lo <= hi:
            if nhit <= k:
                if hi >= slen:
                    break
                ch = ord(s[hi])
                if hits[ch] == 0:
                    nhit += 1
                hits[ch] += 1
                hi += 1
            else:
                if hi-lo-1 > maxwin:
                    maxwin = hi - lo - 1
                    lwin = lo
                    rwin = hi-1
                if lo >= slen:
                    break
                ch = ord(s[lo])
                hits[ch] -= 1
                if hits[ch] == 0:
                    nhit -= 1
                lo += 1
        if nhit <= k:
            if hi-lo > maxwin:
                maxwin = hi - lo
                lwin = lo
                rwin = hi
        return s[lwin:rwin]
    # @param {string} s
    # @param {string} t
    # @return {string}
    def minWindow1(self, s, t):
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
    print solution.maxWindow('ababcbcbaaabbdef', 2)
    print solution.maxWindow('a', 2)
    #print solution.maxWindow('abcdefg', 2)
    #print solution.maxWindow('ababcbcbaaabbddef', 2)
    
    
    
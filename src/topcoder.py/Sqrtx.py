'''

@author: Chauncey
'''
class Solution:
    # @param {integer} x
    # @return {integer}
    def mySqrt(self, x):
        if x < 2: return x
        lo = 1
        hi = x>>1
        while lo < hi:
            m = lo + ((hi - lo + 1) >> 1)
            if m < x/m:
                lo = m
            elif m > x/m:
                hi = m - 1
            else:
                return m
        return lo
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.mySqrt(-2)
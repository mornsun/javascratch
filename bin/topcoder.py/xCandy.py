'''
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Hide Tags Greedy

@author: Chauncey
'''

class Solution:
    # @param {integer[]} ratings
    # @return {integer}
    def candy(self, ratings):
        if ratings is None or len(ratings)==0: return 0
        l = len(ratings)
        incs = [0] * l
        #for i,v in enumerate(ratings):
        inc = 0
        for i in xrange(1,l):
            if ratings[i] > ratings[i-1]:
                inc += 1
                incs[i] = max(inc, incs[i])
            else:
                inc = 0
        inc = 0
        for i in xrange(l-2,-1,-1):
            if ratings[i] > ratings[i+1]:
                inc += 1
                incs[i] = max(inc, incs[i])
            else:
                inc = 0
        return sum(incs)+l
        
                
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.candy([3,2,1,2,3,2])
    
    
    
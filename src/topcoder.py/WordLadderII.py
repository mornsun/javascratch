'''

@author: Chauncey
'''
from collections import deque

class Solution:
    # @param start, a string
    # @param end, a string
    # @param dict, a set of string
    # @return a list of lists of string
    def findLadders(self, start, end, dict):
        if start is None or len(start) == 0: return 0
        if end is None or len(end) == 0: return 0
        map = dict.fromkeys(dict)
        map[start] = None
        map[end] = None
        words = map.keys()
        l = len(words)
        for i in xrange(l):
            word = words[i]
            lword = len(word)
            for j in xrange(i+1,l):
                wd = words[j]
                diff = 0
                for k in xrange(lword):
                    if word[k] != wd[k]:
                        diff += 1
                        if diff != 1:
                            break
                if diff == 1:
                    nexts = map.get(word)
                    if nexts is None:
                        map[word] = [wd]
                    else:
                        nexts.append(wd)
                    nexts = map.get(wd)
                    if nexts is None:
                        map[wd] = [word]
                    else:
                        nexts.append(word)
        dq = deque()
        dq.append(start)
        visited = set()
        find = False
        last = start
        while len(dq) != 0:
            word = dq.popleft()
            if word is end:
                find = True
            if word in visited:
                continue
            visited.add(word)
            nexts = map.get(word)
            if not find and nexts is not None:
                for wd in nexts:
                    dq.append(wd)
            if word is last:
                if len(dq) == 0:
                    break
                else:
                    last = dq[-1]
        if find:
            return deep
        else:
            return 0
                
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.ladderLength('hit', 'cog', ["hot","dot","dog","lot","log"])
    
    
    
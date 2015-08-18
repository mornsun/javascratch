'''

@author: Chauncey
'''
from collections import deque

class Solution:
    # @param {string} beginWord
    # @param {string} endWord
    # @param {set<string>} wordDict
    # @return {integer}
    def ladderLength(self, beginWord, endWord, wordDict):
        if beginWord is None or len(beginWord) == 0: return 0
        if endWord is None or len(endWord) == 0: return 0
        map = dict.fromkeys(wordDict)
        map[beginWord] = None
        map[endWord] = None
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
        dq.append(beginWord)
        visited = set([beginWord])
        deep = 1
        find = False
        last = beginWord
        while len(dq) != 0:
            word = dq.popleft()
            if word is endWord:
                find = True
                break
            nexts = map.get(word)
            if nexts is not None:
                for wd in nexts:
                    if wd not in visited:
                        dq.append(wd)
                        visited.add(wd)
            if word is last:
                deep += 1
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
    
    
    
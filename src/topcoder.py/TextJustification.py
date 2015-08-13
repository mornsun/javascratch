'''

@author: Chauncey
'''
class Solution:
    # @param {string[]} words
    # @param {integer} maxWidth
    # @return {string[]}
    def fullJustify(self, words, maxWidth):
        res = []
        if words is None or len(words) is 0: return res
        line = []
        width = 0
        for word in words:
            l = len(word)
            if l > maxWidth:
                continue
            elif width + l <= maxWidth - len(line):
                width += l
                line.append(word)
            else:
                if len(line) is 1:
                    space = ' ' * (maxWidth - width)
                    res.append(line[0] + space)
                else:
                    space_width = (maxWidth - width) / (len(line) - 1)
                    remain_width = (maxWidth - width) - space_width * (len(line) - 1)
                    space_left = ' ' * (space_width+1)
                    space = ' ' * space_width
                    res.append(('' if remain_width is 0 else space_left.join(line[:remain_width])+space_left) + space.join(line[remain_width:]))
                width = l
                line = [word]
        if len(line):
            if len(line) is 1:
                space = ' ' * (maxWidth - width)
                res.append(line[0] + space)
            else:
                space = ' ' * (maxWidth - width - (len(line) - 1))
                res.append(' '.join(line) + space)
        return res
            
                    
if __name__ == '__main__':
    solution = Solution();
    print solution.fullJustify(["What","must","be","shall","be."], 12)
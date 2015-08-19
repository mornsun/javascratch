'''
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
Hide Tags String

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
    
    
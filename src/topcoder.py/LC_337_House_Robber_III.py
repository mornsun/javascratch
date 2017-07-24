#!/usr/bin/env python
#coding=utf8

'''
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.

Related Topics 
Tree Depth-first Search 
Similar Questions 
House Robber House Robber II 

@author: Chauncey
beat 75.06%
'''

import heapq
import datetime
import time
import sys

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    def rob(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if not root:
            return 0

        return max(self.robDFS(root))

    def robDFS(self, root):
        if not root:
            return [0,0]

        left = self.robDFS(root.left)
        right = self.robDFS(root.right)

        return [max(left)+max(right), root.val+left[0]+right[0]]

    def rob1(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if not root:
            return 0
        memo = {}
        return self.robDFS1(root, memo)

    def robDFS1(self, root, memo):
        if not root:
            return 0
        val = memo.get(root)
        if val is not None:
            return val

        val = 0
        if root.left is not None:
            val += self.robDFS1(root.left.left, memo) + self.robDFS1(root.left.right, memo)
        if root.right is not None:
            val += self.robDFS1(root.right.left, memo) + self.robDFS1(root.right.right, memo)

        ret = max(root.val+val, self.robDFS1(root.left, memo) + self.robDFS1(root.right, memo))
        memo[root] = ret
        return ret
        

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    root = TreeNode(3)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.right = TreeNode(3)
    root.right.right = TreeNode(1)

    print solution.rob(root) #7

    root = TreeNode(3)
    root.left = TreeNode(4)
    root.right = TreeNode(5)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(3)
    root.right.right = TreeNode(1)

    print solution.rob(root) #9
    
    elapsed = datetime.datetime.now() - start_time
    print 'elapsed: ', elapsed.total_seconds()
    #transactions = [buy, sell, cooldown, buy, sell]
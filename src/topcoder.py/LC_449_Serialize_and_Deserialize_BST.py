#!/usr/bin/env python
#coding=utf8

'''
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

Related Topics 
Tree
Similar Questions 
Serialize and Deserialize Binary TreeFind Duplicate Subtrees

@author: Chauncey
beat 24.24%
'''

import heapq
import datetime
import time
import sys
import collections

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        if not root:
            return ''
        res = [str(root.val)]
        currs = [root]
        while currs:
            nexts = []
            for node in currs:
                if node.left:
                    nexts.append(node.left)
                    res.append(str(node.left.val))
                if node.right:
                    nexts.append(node.right)
                    res.append(str(node.right.val))
            currs = nexts

        return ' '.join(res)
        

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        if not data:
            return None

        l = [int(x) for x in data.split()]
        root = TreeNode(l[0])
        for i in xrange(1, len(l)):
            curr = root
            val = l[i]
            while curr:
                if val < curr.val:
                    if curr.left is None:
                        curr.left = TreeNode(val)
                        break
                    curr = curr.left
                else:
                    if curr.right is None:
                        curr.right = TreeNode(val)
                        break
                    curr = curr.right
        return root
        

if __name__ == '__main__':
    start_time = datetime.datetime.now()

    codec = Codec()
    print codec.serialize(codec.deserialize('5 2 6 1 7'))

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
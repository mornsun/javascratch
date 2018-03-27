#!/usr/bin/env python
#coding=utf8

'''
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

Related Topics 
Tree
Similar Questions 
Split BST


    3
   / \
  1   4
   \
    2

    5
   / \
  3   6
 / \   \
2   4   7

    0
     \
      6
     / \
    1   7
     \
     4
    / \
   2   5
@author: Chauncey
beat 69.66%
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

class Solution(object):
    def deleteNode(self, root, key):
        """
        :type root: TreeNode
        :type key: int
        :rtype: TreeNode
        """
        if not root:
            return root

        dummy = TreeNode(root.val)
        dummy.right = root
        prev, curr = self.search(dummy, key)

        if curr:
            r, p, q = prev, curr, curr.left
            while q:
                r, p, q = p, q, q.right
            print prev.val, curr.val, r.val, p.val
            if p == curr:
                #print_tree(dummy.right, 0)
                self.substitute(prev, curr, curr.right)
                #print_tree(dummy.right, 0)
                return dummy.right
            if p == r.right:
                r.right = None
            if p.left:
                if r == curr:
                    r.left = p.left
                else:
                    r.right = p.left
                p.left = None
            self.substitute(prev, curr, p)
        return dummy.right

    def substitute(self, prev, to_del, substtute):
        if substtute != to_del.left:
            substtute.left = to_del.left
        if substtute != to_del.right:
            substtute.right = to_del.right
        if to_del == prev.left:
            prev.left = substtute
        elif to_del == prev.right:
            prev.right = substtute

    def search(self, root, key):
        #print root.val, key
        if root.left:
            if root.left.val == key:
                return (root, root.left)
            prev, curr = self.search(root.left, key)
            if prev:
                return (prev, curr)
        if root.right:
            if root.right.val == key:
                return (root, root.right)
            prev, curr = self.search(root.right, key)
            if prev:
                return (prev, curr)
        return (None, None)

    
def print_tree(node, deep):
    if deep>10:
        return
    indent = ''
    for i in xrange(deep):
        indent += ' '

    if node is None:
        print "%s|-" % (indent)
    else:
        print "%s|-%d" % (indent, node.val)
        print_tree(node.left, deep+1)
        print_tree(node.right, deep+1)
    if deep == 0:
        print

if __name__ == '__main__':
    solution = Solution()
    start_time = datetime.datetime.now()

    root = TreeNode(5)
    root.left = TreeNode(3)
    root.left.left = TreeNode(2)
    root.left.right = TreeNode(4)
    root.right = TreeNode(6)
    root.right.right = TreeNode(7)
    #print_tree(solution.deleteNode(root, 3), 0)
    #print_tree(solution.deleteNode(root, 3), 0)

    root = TreeNode(2)
    root.left = TreeNode(1)
    root.right = TreeNode(3)
    #print_tree(solution.deleteNode(root, 3), 0)

    root = TreeNode(3)
    root.left = TreeNode(1)
    root.right = TreeNode(4)
    root.left.right = TreeNode(2)
    #solution.deleteNode(root, 3)
    #print_tree(solution.deleteNode(root, 3), 0)

    root = TreeNode(5)
    root.left = TreeNode(3)
    root.left.left = TreeNode(2)
    root.left.right = TreeNode(4)
    root.right = TreeNode(6)
    root.right.right = TreeNode(7)
    #print_tree(solution.deleteNode(root, 3), 0)

    root = TreeNode(0)
    root.right = TreeNode(6)
    root.right.left = TreeNode(1)
    root.right.right = TreeNode(7)
    root.right.left.right = TreeNode(4)
    root.right.left.right.left = TreeNode(2)
    root.right.left.right.right = TreeNode(5)
    print_tree(solution.deleteNode(root, 1), 0)

    elapsed = datetime.datetime.now() - start_time
    print 'elapsed:', elapsed.total_seconds()
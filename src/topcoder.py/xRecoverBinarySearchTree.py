'''
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

Hide Tags Tree Depth-first Search

Morris In-order Traversal

@author: Chauncey
'''
# Definition for a binary tree node\
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    # @param {TreeNode} root
    # @return {void} Do not return anything, modify root in-place instead.
    def recoverTree(self, root):
        if root is None: return
        mistake = []
        prev = None
        cur = root
        while cur is not None:
            if cur.left is None:
                self.detect(mistake, prev, cur)
                prev = cur
                cur = cur.right
            else:
                node = cur.left
                while node.right is not None and node.right is not cur:
                    node = node.right
                if node.right is None:
                    node.right = cur
                    cur = cur.left
                else:
                    self.detect(mistake, prev, cur)
                    node.right = None
                    prev = cur
                    cur = cur.right
        print mistake[0].val, mistake[1].val
        mistake[0].val, mistake[1].val = mistake[1].val, mistake[0].val
                
    
    def detect(self, mistake, prev, cur):
        if prev is not None and prev.val > cur.val:
            if len(mistake) == 0:
                mistake.append(prev)
                mistake.append(cur)
            else:
                mistake[1] = cur
                    
if __name__ == '__main__':
    solution = Solution();
    root = TreeNode(5)
    root.left = TreeNode(2)
    root.left.left = TreeNode(1)
    root.left.left.left = TreeNode(0)
    root.left.left.right = TreeNode(3)
    root.left.right = TreeNode(4)
    root.right = TreeNode(7)
    print solution.recoverTree(root)
    
    
    